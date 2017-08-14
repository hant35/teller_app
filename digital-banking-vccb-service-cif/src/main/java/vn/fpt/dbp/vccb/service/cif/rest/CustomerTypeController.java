package vn.fpt.dbp.vccb.service.cif.rest;

import java.security.Principal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerType;
import vn.fpt.dbp.vccb.core.domain.customer.QCustomerType;
import vn.fpt.dbp.vccb.core.domain.customer.repository.CustomerTypeRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class CustomerTypeController {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";

	@Value("${spring.application.name}")
	private String serviceName;

	@Autowired
	protected CustomerTypeRepository customerTypeRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/customertype/list", method = RequestMethod.GET, produces = "application/json")
	public List<CustomerType> listCustomerType(Principal principle) {
		return customerTypeRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/customertype/detail/id/{id}", method = RequestMethod.GET, produces = "application/json")
	public CustomerType customerTypeInfoById(Principal principle, @PathVariable("id") Long id) {
		CustomerType customerType = customerTypeRepository.findOne(id);

		return customerType;
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/customertype/detail/code/{code}", method = RequestMethod.GET, produces = "application/json")
	public CustomerType customerTypeInfoByCode(Principal principle, @PathVariable("code") String code) {
		CustomerType customerType = customerTypeRepository.findByCodeIgnoreCase(code);

		return customerType;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/customertype/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<CustomerType> searchCustomerType(Principal principle, @RequestBody CustomerType customerType,
			Pageable pageable) {
		QCustomerType qCustomerType = QCustomerType.customerType;
		BooleanExpression booleanExpression = qCustomerType.id.isNotNull();

		if (StringUtils.isNotEmpty(customerType.getCode())) {
			booleanExpression = booleanExpression
					.and(qCustomerType.code.toUpperCase().like(customerType.getCode().toUpperCase()));
		}

		if (StringUtils.isNotEmpty(customerType.getName())) {
			booleanExpression = booleanExpression
					.and(qCustomerType.name.toUpperCase().like(customerType.getName().toUpperCase()));
		}

		Page<CustomerType> result = customerTypeRepository.findAll(booleanExpression, pageable);

		return new PagedResource<CustomerType>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages());

	}

}
