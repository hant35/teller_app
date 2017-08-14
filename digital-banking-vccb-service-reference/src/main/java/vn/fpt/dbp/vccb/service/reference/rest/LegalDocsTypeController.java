package vn.fpt.dbp.vccb.service.reference.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.customer.LegalDocsType;
import vn.fpt.dbp.vccb.core.domain.customer.QLegalDocsType;
import vn.fpt.dbp.vccb.core.domain.customer.repository.LegalDocsTypeRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
public class LegalDocsTypeController {

	@Autowired
	LegalDocsTypeRepository legalDocsTypeRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/legaldocstype/list", method = RequestMethod.GET, produces = "application/json")
	public List<LegalDocsType> listLegalDocsTypes(Principal principle) {
		return legalDocsTypeRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/legaldocstype/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<LegalDocsType> searchLegalDocsTypes(Principal principle, @RequestBody LegalDocsType request,
			Pageable pageable) {
		QLegalDocsType qLegalDocsType = QLegalDocsType.legalDocsType;
		BooleanExpression booleanExpression = qLegalDocsType.id.isNotNull();
		if (!StringUtils.isEmpty(request.getCode())) {
			booleanExpression = booleanExpression
					.and(qLegalDocsType.code.toUpperCase().like(request.getCode().toUpperCase()));
		}
		Page<LegalDocsType> result = legalDocsTypeRepository.findAll(booleanExpression, pageable);
		return new PagedResource<LegalDocsType>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}
}
