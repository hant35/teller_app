package vn.fpt.dbp.vccb.service.reference.rest;

import java.security.Principal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.system.CodeMaster;
import vn.fpt.dbp.vccb.core.domain.system.QCodeMaster;
import vn.fpt.dbp.vccb.core.domain.system.repository.CodeMasterRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
public class CodeMasterController {

	// @Autowired
	// private EventBus eventBus;

	@Autowired
	private CodeMasterRepository codeMasterRepository;

	@RequestMapping(value = "/api/code/list/{code}", method = RequestMethod.GET, produces = "application/json")
	public List<CodeMaster> listCodes(Principal principle, @PathVariable("code") String code) {
		return codeMasterRepository.findByCodeAndIsDeleteFalseOrderBySortNoAsc(code);
	}

	@RequestMapping(value = "/api/code/listall/{code}", method = RequestMethod.GET, produces = "application/json")
	public List<CodeMaster> listAllCodes(Principal principle, @PathVariable("code") String code) {
		return codeMasterRepository.findByCodeOrderBySortNoAsc(code);
	}

	@RequestMapping(value = "/api/code/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<CodeMaster> searchCode(Principal principle, @RequestBody CodeMaster codeMaster,
			Pageable pageable) {
		QCodeMaster qCodeMaster = QCodeMaster.codeMaster;
		BooleanExpression booleanExpression = qCodeMaster.id.isNotNull();
		if (StringUtils.isNotEmpty(codeMaster.getCode())) {
			booleanExpression = booleanExpression
					.and(qCodeMaster.code.upper().like(codeMaster.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(codeMaster.getName())) {
			booleanExpression = booleanExpression
					.and(qCodeMaster.name.upper().like(codeMaster.getName().toUpperCase()));
		}
		if (codeMaster.isDelete()) {
			booleanExpression = booleanExpression.and(qCodeMaster.isDelete.eq(codeMaster.isDelete()));
		}

		Page<CodeMaster> result = codeMasterRepository.findAll(booleanExpression, pageable);

		return new PagedResource<CodeMaster>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());

	}

}
