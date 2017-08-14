package vn.fpt.dbp.vccb.service.cif.rest;

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

import vn.fpt.dbp.vccb.core.domain.customer.CustomerRefPerson;
import vn.fpt.dbp.vccb.core.domain.customer.QCustomerRefPerson;
import vn.fpt.dbp.vccb.core.domain.customer.repository.CustomerRefPersonRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class CustomerRefPersonController {
	
	@Autowired
	private CustomerRefPersonRepository customerRefPersonRepository;
	
	@RequestMapping(value = "/api/customerrefperson/list", method = RequestMethod.GET, produces = "application/json")
	public List<CustomerRefPerson> listCustomerRefPerson(Principal principle) {
		return customerRefPersonRepository.findAll();
	}
	
	@RequestMapping(value = "/api/customerrefperson/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public CustomerRefPerson customerGroupInfo(Principal principle, @PathVariable("id") Long id) {
		CustomerRefPerson CustomerGroup = customerRefPersonRepository.findOne(id);
		
		return CustomerGroup;
	}
	
	@RequestMapping(value = "/api/customerrefperson/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<CustomerRefPerson> searchCustomerRefPerson(Principal principle, @RequestBody CustomerRefPerson customerRefPerson, Pageable pageable) {
		QCustomerRefPerson qCustomerRefPerson = QCustomerRefPerson.customerRefPerson;
		BooleanExpression booleanExpression = qCustomerRefPerson.fullName.like("%");
		
		if (StringUtils.isNotEmpty(customerRefPerson.getFullName())) {
			booleanExpression = booleanExpression.and(qCustomerRefPerson.fullName.upper().like(customerRefPerson.getFullName().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(customerRefPerson.getShortName())) {
			booleanExpression = booleanExpression.and(qCustomerRefPerson.shortName.upper().like(customerRefPerson.getShortName().toUpperCase()));
		}
		if(StringUtils.isNotEmpty(customerRefPerson.getLegalDocsNumber())){
			booleanExpression = booleanExpression.and(qCustomerRefPerson.legalDocsNumber.upper().like(customerRefPerson.getLegalDocsNumber().toUpperCase()));
		}
		if(StringUtils.isNotEmpty(customerRefPerson.getLegalDocsNumber2())){
			booleanExpression = booleanExpression.and(qCustomerRefPerson.legalDocsNumber2.upper().like(customerRefPerson.getLegalDocsNumber2().toUpperCase()));
		}
		if (StringUtils.isNotEmpty(customerRefPerson.getRefPersonType())) {
			booleanExpression = booleanExpression.and(qCustomerRefPerson.refPersonType.like(customerRefPerson.getRefPersonType().toUpperCase()));
		}
		if(customerRefPerson.getCustomer() != null){
			if(customerRefPerson.getCustomer().getBranch() != null){
				booleanExpression = booleanExpression.and(qCustomerRefPerson.customer.branch.eq(customerRefPerson.getCustomer().getBranch()));
			}

		}
		
		Page<CustomerRefPerson> result = customerRefPersonRepository.findAll(booleanExpression, pageable);
		
		return new PagedResource<CustomerRefPerson>(result.getContent(), result.getNumber(), result.getSize(), result.getTotalPages(),result.getTotalElements());
		
	}

}
