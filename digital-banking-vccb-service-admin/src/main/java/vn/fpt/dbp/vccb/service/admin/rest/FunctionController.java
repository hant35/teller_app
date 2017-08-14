package vn.fpt.dbp.vccb.service.admin.rest;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.system.Function;
import vn.fpt.dbp.vccb.core.domain.system.QFunction;
import vn.fpt.dbp.vccb.core.domain.system.repository.FunctionRepository;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;


@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class FunctionController {
	
	//@Autowired
	//private EventBus eventBus;

	@Autowired
	private FunctionRepository functionRepository;
	

	
	@RequestMapping(value = "/api/function/list", method = RequestMethod.GET, produces = "application/json")
	public List<Function> listFunctions(Principal principle) {
		return functionRepository.findAll();
	}
	
	@RequestMapping(value = "/api/function/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public Function functionInfo(Principal principle, @PathVariable("id") Long id) {
		Function Function = functionRepository.findOne(id);
		
		return Function;
	}
	
	@RequestMapping(value = "/api/function/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Function> searchFunction(Principal principle, @RequestBody Function function, Pageable pageable) {
		QFunction qFunction = QFunction.function;
		BooleanExpression booleanExpression = qFunction.name.like("%");
		if (StringUtils.isNotEmpty(function.getCode())) {
			booleanExpression = booleanExpression.and(qFunction.code.upper().like(function.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(function.getName())) {
			booleanExpression = booleanExpression.and(qFunction.name.upper().like(function.getName().toUpperCase()));
		}
		
		if (StringUtils.isNotEmpty(function.getModule())) {
			booleanExpression = booleanExpression.and(qFunction.module.upper().like(function.getModule().toUpperCase()));
		}
		/* Co them dk !"%".equalsIgnoreCase(function.getScreen()) de xu ly cho truong hop truong SCREEN duoi DB is null
		 * 
		 */
		if (StringUtils.isNotEmpty(function.getScreen()) && !"%".equalsIgnoreCase(function.getScreen())) {
			booleanExpression = booleanExpression.and(qFunction.screen.upper().like(function.getScreen().toUpperCase()));
		}
		
		Page<Function> result = functionRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Function>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages());
		
	}
	
	@RequestMapping(value = "/api/function/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Function> createFunction(Principal principle, @RequestBody @Valid Function request) {
		RestResponse<Function> resp = new RestResponse<Function>();
		try {
			Function function = request.getId() == null ? null : functionRepository.findOne(request.getId());
			if (function != null) {
				resp.setStatus(RestResponse.STATUS_ERROR);
				resp.setErrorMessage("This record is exist");
				resp.setContent(request);
			}
			else {
				functionRepository.save(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage("");
				resp.setContent(request);
				
//				FunctionCreatedEvent event = new FunctionCreatedEvent(request);
//				eventBus.publish(asEventMessage(event));
			}
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@RequestMapping(value = "/api/function/modify", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Function> modifyFunction(Principal principle, @RequestBody @Valid Function request) {
		RestResponse<Function> resp = new RestResponse<Function>();
		try {
			NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
			Function function = functionRepository.findOne(request.getId());
			beanUtils.copyProperties(function, request);
			
			functionRepository.save(function);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(function);
			
//			FunctionModifiedEvent event = new FunctionModifiedEvent(function);
//			eventBus.publish(asEventMessage(event));
			
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@RequestMapping(value = "/api/function/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Function> deleteFunction(Principal principle, @RequestBody @Valid Function request) {
		RestResponse<Function> resp = new RestResponse<Function>();
		try {
			Function deletedFunction = functionRepository.findOne(request.getId());
			
			functionRepository.save(deletedFunction);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(deletedFunction);
			
//			FunctionDeletedEvent event = new FunctionDeletedEvent(deletedFunction);
//			eventBus.publish(asEventMessage(event));
			
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
}
