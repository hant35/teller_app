package vn.fpt.dbp.vccb.service.reference.rest;

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

import vn.fpt.dbp.vccb.core.domain.organization.Branch;
import vn.fpt.dbp.vccb.core.domain.organization.QBranch;
import vn.fpt.dbp.vccb.core.domain.organization.repository.BranchRepository;
import vn.fpt.util.NullAwareBeanUtilsBean;
import vn.fpt.util.rest.PagedResource;
import vn.fpt.util.rest.RestResponse;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class BranchController {

	// @Autowired
	// private EventBus eventBus;

	@Autowired
	private BranchRepository branchRepository;

	@RequestMapping(value = "/api/branch/list", method = RequestMethod.GET, produces = "application/json")
	public List<Branch> listBranches(Principal principle) {
		return branchRepository.findAll();
	}

	@RequestMapping(value = "/api/branch/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public Branch branchInfo(Principal principle, @PathVariable("id") Long id) {
		Branch Branch = branchRepository.findOne(id);

		return Branch;
	}

	@RequestMapping(value = "/api/branch/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Branch> searchBranch(Principal principle, @RequestBody Branch branch, Pageable pageable) {
		QBranch qBranch = QBranch.branch;
		BooleanExpression booleanExpression = qBranch.name.like("%");
		if (StringUtils.isNotEmpty(branch.getCode())) {
			booleanExpression = booleanExpression.and(qBranch.code.upper().like(branch.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(branch.getName())) {
			booleanExpression = booleanExpression.and(qBranch.name.upper().like(branch.getName().toUpperCase()));
		}

		if (branch.getArea() != null) {
			booleanExpression = booleanExpression.and(qBranch.area.id.eq(branch.getArea().getId()));
		}

		Page<Branch> result = branchRepository.findAll(booleanExpression, pageable);

		return new PagedResource<Branch>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages());

	}

	@RequestMapping(value = "/api/branch/create", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Branch> createBranch(Principal principle, @RequestBody @Valid Branch request) {
		RestResponse<Branch> resp = new RestResponse<Branch>();
		try {
			Branch branch = request.getId() == null ? null : branchRepository.findOne(request.getId());
			if (branch != null) {
				resp.setStatus(RestResponse.STATUS_ERROR);
				resp.setErrorMessage("This record is exist");
				resp.setContent(request);
			} else {
				branchRepository.save(request);
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

	@RequestMapping(value = "/api/branch/modify", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Branch> modifyBranch(Principal principle, @RequestBody @Valid Branch request) {
		RestResponse<Branch> resp = new RestResponse<Branch>();
		try {
			NullAwareBeanUtilsBean beanUtils = new NullAwareBeanUtilsBean();
			Branch branch = branchRepository.findOne(request.getId());
			beanUtils.copyProperties(branch, request);

			branchRepository.save(branch);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(branch);

			// BranchModifiedEvent event = new BranchModifiedEvent(branch);
			// eventBus.publish(asEventMessage(event));

		} catch (Exception e) {
			resp.setStatus(RestResponse.STATUS_ERROR);
			resp.setErrorMessage(e.getLocalizedMessage());
			resp.setContent(null);
		}
		return resp;
	}

	@RequestMapping(value = "/api/branch/delete", method = RequestMethod.POST, produces = "application/json")
	public RestResponse<Branch> deleteBranch(Principal principle, @RequestBody @Valid Branch request) {
		RestResponse<Branch> resp = new RestResponse<Branch>();
		try {
			Branch deletedBranch = branchRepository.findOne(request.getId());

			branchRepository.save(deletedBranch);
			resp.setStatus(RestResponse.STATUS_SUCCESS);
			resp.setErrorMessage("");
			resp.setContent(deletedBranch);

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
