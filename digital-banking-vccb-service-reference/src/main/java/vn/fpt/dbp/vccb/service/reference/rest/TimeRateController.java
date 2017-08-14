package vn.fpt.dbp.vccb.service.reference.rest;

import java.security.Principal;
import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.interest.QTimeRate;
import vn.fpt.dbp.vccb.core.domain.interest.TimeRate;
import vn.fpt.dbp.vccb.core.domain.interest.repository.TimeRateRepository;
import vn.fpt.dbp.vccb.core.domain.product.Product;
import vn.fpt.dbp.vccb.core.domain.product.ProductAccountClass;
import vn.fpt.dbp.vccb.core.domain.product.repository.ProductRepository;
import vn.fpt.util.rest.PagedResource;

import javax.validation.Valid;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class TimeRateController {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";

	@Value("${spring.application.name}")
	private String serviceName;

	@Autowired
	protected TimeRateRepository timeRateRepository;

	@Autowired
	protected ProductRepository productRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/timerate/list", method = RequestMethod.GET, produces = "application/json")
	public List<TimeRate> listCustomerType(Principal principle) {
		return timeRateRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/timerate/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<TimeRate> searchTimeRate(Principal principle, @RequestBody TimeRate timeRate,
			Pageable pageable) {
		QTimeRate qTimeRate = QTimeRate.timeRate;

		BooleanExpression booleanExpression = qTimeRate.id.isNotNull();

		if (StringUtils.isNotEmpty(timeRate.getCode())) {
			booleanExpression = booleanExpression.and(qTimeRate.code.like(timeRate.getCode()));
		}

		if (StringUtils.isNotEmpty(timeRate.getName())) {
			booleanExpression = booleanExpression.and(qTimeRate.name.like(timeRate.getName()));
		}

		Page<TimeRate> result = timeRateRepository.findAll(booleanExpression, pageable);

		return new PagedResource<TimeRate>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages());

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/timerate/searchbyproduct", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<TimeRate> searchTimeRateByProduct(Principal principle, @RequestBody @Valid Product request, Pageable pageable) {
		Set<TimeRate> timeRateSet = new HashSet<TimeRate>();
		Product product = null;
		Currency currencyRequest = null;
		TimeRate timeRateRequest = null;
		TimeRate timeRate = null;

		if (request.getId() != null) {
			product = productRepository.findOne(request.getId());
		}

		if (request.getProductAccountClasses() != null && request.getProductAccountClasses().size() > 0) {
			for (ProductAccountClass productAccountClass : request.getProductAccountClasses()) {
				currencyRequest = productAccountClass.getCurrency();
				timeRateRequest = productAccountClass.getTimeRate();
				break;
			}
		}

		if (product != null && currencyRequest != null) {
			if (product.getProductAccountClasses() != null && product.getProductAccountClasses().size() > 0) {
				for (ProductAccountClass productAccountClass : product.getProductAccountClasses()
						) {
					if (currencyRequest.equals(productAccountClass.getCurrency())
							&& productAccountClass.getTimeRate() != null) {
						if (productAccountClass.getTimeRate().getCode() != null && timeRateRequest != null) {
							if (timeRateRequest.getCode() != null) {
								if (productAccountClass.getTimeRate().getCode().toUpperCase().contains(timeRateRequest.getCode().toUpperCase())) {
									timeRateSet.add(productAccountClass.getTimeRate());
								}
							}
						} else {
//						    timeRate = timeRateRepository.findOne(productAccountClass.getTimeRate().getId());
							timeRateSet.add(productAccountClass.getTimeRate());
						}
					}
				}
			}
		}
		if (timeRateSet == null && timeRateSet.size() <= 0) {
			return new PagedResource<TimeRate>(new ArrayList<TimeRate>() , pageable.getPageNumber(), pageable.getPageSize(),0);
		}

		int start = pageable.getOffset();
		int end = (start + pageable.getPageSize()) > timeRateSet.size() ? timeRateSet.size() : (start + pageable.getPageSize());
		int totalPages = (int) Math.ceil((double) timeRateSet.size() / pageable.getPageSize());

		List<TimeRate> timeRateList = new ArrayList<TimeRate>(timeRateSet);
		try{
			PagedResource<TimeRate> timeRatePagedResource = new PagedResource<TimeRate>(timeRateList.subList(start, end), pageable.getPageNumber(), pageable.getPageSize(), totalPages);
			return timeRatePagedResource;
		}catch (Exception e){
			return new PagedResource<TimeRate>(new ArrayList<TimeRate>(), pageable.getPageNumber(), pageable.getPageSize(),0);
		}
	}

}
