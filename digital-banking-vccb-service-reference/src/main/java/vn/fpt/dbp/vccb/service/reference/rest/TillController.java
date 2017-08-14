package vn.fpt.dbp.vccb.service.reference.rest;

import java.security.Principal;
import java.util.List;

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

import vn.fpt.dbp.vccb.core.domain.currency.QTill;
import vn.fpt.dbp.vccb.core.domain.currency.Till;
import vn.fpt.dbp.vccb.core.domain.currency.repository.TillRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class TillController {
	
	@Autowired
	private TillRepository tillRepository;
	
	@RequestMapping(value = "/api/till/list", method = RequestMethod.GET, produces = "application/json")
	public List<Till> listTills(Principal principle) {
		return tillRepository.findAll();
	}
	
	@RequestMapping(value = "/api/till/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public Till tillInfo(Principal principle, @PathVariable("id") Long id) {
		Till till = tillRepository.findOne(id);
		
		return till;
	}
	
	@RequestMapping(value = "/api/till/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Till> searchCustomerGroup(Principal principle, @RequestBody Till till, Pageable pageable) {
		QTill qTill = QTill.till;
		BooleanExpression booleanExpression = qTill.name.like("%");
		if (StringUtils.isNotEmpty(till.getCode())) {
			booleanExpression = booleanExpression.and(qTill.code.upper().like(till.getCode().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(till.getName())) {
			booleanExpression = booleanExpression.and(qTill.name.upper().like(till.getName().toUpperCase()));
		}
		
		if(till.getVault() != null && till.getVault().getBranch() != null)
		{
			booleanExpression = booleanExpression.and(qTill.vault.branch.id.eq(till.getVault().getBranch().getId()));
		}
		
		Page<Till> result = tillRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<Till>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(), result.getTotalElements());
		
	}
}
