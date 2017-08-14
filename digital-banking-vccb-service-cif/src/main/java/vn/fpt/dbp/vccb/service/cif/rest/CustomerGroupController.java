package vn.fpt.dbp.vccb.service.cif.rest;

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

import vn.fpt.dbp.vccb.core.domain.customer.CustomerGroup;
import vn.fpt.dbp.vccb.core.domain.customer.QCustomerGroup;
import vn.fpt.dbp.vccb.core.domain.customer.repository.CustomerGroupRepository;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;


@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class CustomerGroupController {
	
	//@Autowired
	//private EventBus eventBus;

	@Autowired
	private CustomerGroupRepository customerGroupRepository;
	

	
	@RequestMapping(value = "/api/customergroup/list", method = RequestMethod.GET, produces = "application/json")
	public List<CustomerGroup> listCustomerGroups(Principal principle) {
		return customerGroupRepository.findAll();
	}
	
	@RequestMapping(value = "/api/customergroup/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public CustomerGroup customerGroupInfo(Principal principle, @PathVariable("id") Long id) {
		CustomerGroup CustomerGroup = customerGroupRepository.findOne(id);
		
		return CustomerGroup;
	}
	
	@RequestMapping(value = "/api/customergroup/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<CustomerGroup> searchCustomerGroup(Principal principle, @RequestBody CustomerGroup customerGroup, Pageable pageable) {
		QCustomerGroup qCustomerGroup = QCustomerGroup.customerGroup;
		BooleanExpression booleanExpression = qCustomerGroup.name.like("%");
		if (StringUtils.isNotEmpty(customerGroup.getCode())) {
			booleanExpression = booleanExpression.and(qCustomerGroup.code.upper().like(customerGroup.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(customerGroup.getName())) {
			booleanExpression = booleanExpression.and(qCustomerGroup.name.upper().like(customerGroup.getName().toUpperCase()));
		}
		if(customerGroup.getCustomerType() != null){
			if(customerGroup.getCustomerType().getId() != null){
				booleanExpression = booleanExpression.and(qCustomerGroup.customerType.id.eq(customerGroup.getCustomerType().getId()));
			}
			if(customerGroup.getCustomerType().getCode() != null){
				booleanExpression = booleanExpression.and(qCustomerGroup.customerType.code.eq(customerGroup.getCustomerType().getCode()));
			}
		}
		
		Page<CustomerGroup> result = customerGroupRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<CustomerGroup>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages());
		
	}
	
	@RequestMapping(value = "/api/customergroup/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<CustomerGroup> createCustomerGroup(Principal principle, @RequestBody @Valid CustomerGroup request) {
		RestResponse<CustomerGroup> resp = new RestResponse<CustomerGroup>();
		try {
			CustomerGroup customerGroup = request.getId() == null ? null : customerGroupRepository.findOne(request.getId());
			if (customerGroup != null) {
				resp.setStatus(RestResponse.STATUS_ERROR);
				resp.setErrorMessage("This record is exist");
				resp.setContent(request);
			}
			else {
				customerGroupRepository.save(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage("");
				resp.setContent(request);
				
//				CustomerGroupCreatedEvent event = new CustomerGroupCreatedEvent(request);
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
	
	@RequestMapping(value = "/api/customergroup/modify", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<CustomerGroup> modifyCustomerGroup(Principal principle, @RequestBody @Valid CustomerGroup request) {
		RestResponse<CustomerGroup> resp = new RestResponse<CustomerGroup>();
		try {
			NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
			CustomerGroup customerGroup = customerGroupRepository.findOne(request.getId());
			beanUtils.copyProperties(customerGroup, request);
			
			customerGroupRepository.save(customerGroup);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(customerGroup);
			
//			CustomerGroupModifiedEvent event = new CustomerGroupModifiedEvent(customerGroup);
//			eventBus.publish(asEventMessage(event));
			
		} 
		catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}
	
	@RequestMapping(value = "/api/customergroup/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<CustomerGroup> deleteCustomerGroup(Principal principle, @RequestBody @Valid CustomerGroup request) {
		RestResponse<CustomerGroup> resp = new RestResponse<CustomerGroup>();
		try {
			CustomerGroup deletedCustomerGroup = customerGroupRepository.findOne(request.getId());
			
			customerGroupRepository.save(deletedCustomerGroup);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(deletedCustomerGroup);
			
//			CustomerGroupDeletedEvent event = new CustomerGroupDeletedEvent(deletedCustomerGroup);
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
