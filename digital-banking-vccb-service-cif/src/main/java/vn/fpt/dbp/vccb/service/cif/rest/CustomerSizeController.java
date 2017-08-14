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

import vn.fpt.dbp.vccb.core.domain.customer.CustomerSize;
import vn.fpt.dbp.vccb.core.domain.customer.QCustomerSize;
import vn.fpt.dbp.vccb.core.domain.customer.repository.CustomerSizeRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class CustomerSizeController {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";

	@Value("${spring.application.name}")
	private String serviceName;

	@Autowired
	protected CustomerSizeRepository customerSizeRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/customersize/list", method = RequestMethod.GET, produces = "application/json")
	public List<CustomerSize> listCustomerSize(Principal principle) {
		return customerSizeRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/customersize/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public CustomerSize customerSizeInfo(Principal principle, @PathVariable("id") Long id) {
		CustomerSize customerSize = customerSizeRepository.findOne(id);

		return customerSize;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/customersize/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<CustomerSize> searchCustomerSize(Principal principle, @RequestBody CustomerSize customerSize,
			Pageable pageable) {
		QCustomerSize qCustomerSize = QCustomerSize.customerSize;
		BooleanExpression booleanExpression = qCustomerSize.id.isNotNull();

		if (StringUtils.isNotEmpty(customerSize.getCode())) {
			booleanExpression = booleanExpression
					.and(qCustomerSize.code.toUpperCase().like(customerSize.getCode().toUpperCase()));
		}

		if (StringUtils.isNotEmpty(customerSize.getName())) {
			booleanExpression = booleanExpression
					.and(qCustomerSize.name.toUpperCase().like(customerSize.getName().toUpperCase()));
		}

		Page<CustomerSize> result = customerSizeRepository.findAll(booleanExpression, pageable);

		return new PagedResource<CustomerSize>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages());

	}
}
