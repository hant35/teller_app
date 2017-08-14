package vn.fpt.dbp.vccb.service.system.rest;

import java.security.Principal;

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

import vn.fpt.dbp.vccb.core.domain.system.DataTable;
import vn.fpt.dbp.vccb.core.domain.system.QDataTable;
import vn.fpt.dbp.vccb.core.domain.system.repository.DataTableRepository;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class DataTableController {
	
	@Autowired
	private DataTableRepository dataTableRePository;
	
	@RequestMapping(value = "/api/datatable/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public DataTable branchInfo(Principal principle, @PathVariable("id") Long id) {
		DataTable datatable = dataTableRePository.findOne(id);

		return datatable;
	}
	
	@RequestMapping(value = "/api/datatable/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<DataTable> searchBranch(Principal principle, @RequestBody DataTable datatable, Pageable pageable) {
		QDataTable qDataTable = QDataTable.dataTable;
		BooleanExpression booleanExpression = qDataTable.screenCode.like("%");
		if (StringUtils.isNotEmpty(datatable.getScreenCode())) {
			booleanExpression = booleanExpression.and(qDataTable.screenCode.upper().like(datatable.getScreenCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(datatable.getTableCode())) {
			booleanExpression = booleanExpression.and(qDataTable.tableCode.upper().like(datatable.getTableCode().toUpperCase()));
		}

		Page<DataTable> result = dataTableRePository.findAll(booleanExpression, pageable);

		return new PagedResource<DataTable>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages());

	}
	
	@RequestMapping(value = "/api/datatable/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<DataTable> createBranch(Principal principle, @RequestBody @Valid DataTable request) {
		RestResponse<DataTable> resp = new RestResponse<DataTable>();
		try {
			DataTable dataTable = request.getId() == null ? null : dataTableRePository.findOne(request.getId());
			if (dataTable != null) {
				resp.setStatus(RestResponse.STATUS_ERROR);
				resp.setErrorMessage("This record is exist");
				resp.setContent(request);
			} else {
				dataTableRePository.save(request);
				resp.setStatus(RestResponse.STATUS_SUCCESS);
				resp.setErrorMessage("");
				resp.setContent(request);

				// BranchCreatedEvent event = new BranchCreatedEvent(request);
				// eventBus.publish(asEventMessage(event));
			}
		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@RequestMapping(value = "/api/datatable/modify", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<DataTable> modifyBranch(Principal principle, @RequestBody @Valid DataTable request) {
		RestResponse<DataTable> resp = new RestResponse<DataTable>();
		try {
			NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
			DataTable dataTable = dataTableRePository.findOne(request.getId());
			beanUtils.copyProperties(dataTable, request);

			dataTableRePository.save(dataTable);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(dataTable);

			// BranchModifiedEvent event = new BranchModifiedEvent(branch);
			// eventBus.publish(asEventMessage(event));

		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@RequestMapping(value = "/api/datatable/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<DataTable> deleteBranch(Principal principle, @RequestBody @Valid DataTable request) {
		RestResponse<DataTable> resp = new RestResponse<DataTable>();
		try {
			DataTable dataTable = dataTableRePository.findOne(request.getId());

			dataTableRePository.save(dataTable);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(dataTable);

			// BranchDeletedEvent event = new BranchDeletedEvent(deletedBranch);
			// eventBus.publish(asEventMessage(event));

		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

}
