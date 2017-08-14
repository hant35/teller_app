package vn.fpt.dbp.vccb.service.param.rest;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import vn.fpt.dbp.vccb.core.domain.organization.Area;
import vn.fpt.dbp.vccb.core.domain.organization.QArea;
import vn.fpt.dbp.vccb.core.domain.organization.repository.AreaRepository;
import vn.fpt.dbp.vccb.core.domain.product.AccountClass;
import vn.fpt.dbp.vccb.core.domain.product.QAccountClass;
import vn.fpt.dbp.vccb.core.domain.product.repository.AccountClassRepository;
import vn.fpt.util.rest.PagedResource;

import java.security.Principal;
import java.util.List;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class AccountClassController {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";

	@Value("${spring.application.name}")
	private String serviceName;

	@Autowired
	protected AccountClassRepository accountClassRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/accountclass/list", method = RequestMethod.GET, produces = "application/json")
	public List<AccountClass> list(Principal principle) {
		return accountClassRepository.findAll();
	}


	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/accountclass/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<AccountClass> search(Principal principle, @RequestBody AccountClass accountClass, Pageable pageable) {
		QAccountClass qAccountClass = QAccountClass.accountClass;
		BooleanExpression booleanExpression = qAccountClass.id.isNotNull();

		Page<AccountClass> result = accountClassRepository.findAll(booleanExpression, pageable);

		return new PagedResource<AccountClass>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages());
	}
}
