package vn.fpt.dbp.vccb.service.reference.rest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.interest.TimeRate;
import vn.fpt.dbp.vccb.core.domain.organization.Area;
import vn.fpt.dbp.vccb.core.domain.organization.QArea;
import vn.fpt.dbp.vccb.core.domain.organization.repository.AreaRepository;
import vn.fpt.dbp.vccb.core.domain.product.Product;
import vn.fpt.dbp.vccb.core.domain.product.ProductAccountClass;
import vn.fpt.dbp.vccb.core.domain.product.repository.ProductRepository;
import vn.fpt.util.rest.PagedResource;

import javax.validation.Valid;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class AreaController {
	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";

	@Value("${spring.application.name}")
	private String serviceName;

	@Autowired
	protected AreaRepository areaRepository;

	@Autowired
	protected ProductRepository productRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/area/list", method = RequestMethod.GET, produces = "application/json")
	public List<Area> listArea(Principal principle) {
		return areaRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/area/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public Area areaInfo(Principal principle, @PathVariable("id") Long id) {
		Area area = areaRepository.findOne(id);

		return area;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/area/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Area> searchArea(Principal principle, @RequestBody Area area, Pageable pageable) {
		QArea qArea = QArea.area;
		BooleanExpression booleanExpression = qArea.id.isNotNull();

		if (StringUtils.isNotEmpty(area.getCode())) {
			booleanExpression = booleanExpression.and(qArea.code.toUpperCase().like(area.getCode().toUpperCase()));
		}

		if (StringUtils.isNotEmpty(area.getName())) {
			booleanExpression = booleanExpression.and(qArea.name.toUpperCase().like(area.getName().toUpperCase()));
		}

		Page<Area> result = areaRepository.findAll(booleanExpression, pageable);

		return new PagedResource<Area>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages());

	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/area/searchbyproduct", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Area> searchAreaByProduct(Principal principle, @RequestBody @Valid Product request, Pageable pageable) {
		Set<Area> areaSet = new HashSet<Area>();
		Product product = null;
		Currency currencyRequest = null;
		TimeRate timeRateRequest = null;
		Area areaRequest = null;
		Area area = null;

		if(request.getId() != null)
		{
			product = productRepository.findOne(request.getId());
		}

		if( request.getProductAccountClasses() != null && request.getProductAccountClasses().size() > 0) {
			for (ProductAccountClass productAccountClass : request.getProductAccountClasses()) {
				currencyRequest = productAccountClass.getCurrency();
				timeRateRequest = productAccountClass.getTimeRate();
				areaRequest = productAccountClass.getArea();
				break;
			}
		}

		if(product != null && currencyRequest != null && timeRateRequest != null) {
			if (product.getProductAccountClasses() != null && product.getProductAccountClasses().size() > 0) {
				for (ProductAccountClass productAccountClass : product.getProductAccountClasses())
				{
					if( currencyRequest.equals(productAccountClass.getCurrency())
						&&	timeRateRequest.equals(productAccountClass.getTimeRate())
						&&  productAccountClass.getArea() != null )
					{
						if( productAccountClass.getArea().getCode() != null &&  areaRequest != null  )
						{
							if(areaRequest.getCode() != null) {
								if (productAccountClass.getArea().getCode().toUpperCase().contains(areaRequest.getCode().toUpperCase())) {
									areaSet.add(productAccountClass.getArea());
								}
							}
						}else
						{
//							area = areaRepository.findOne(productAccountClass.getArea().getId());
							areaSet.add(productAccountClass.getArea());
						}
					}
				}
			}
		}

		if(areaSet == null && areaSet.size() <= 0)
		{
			return  new PagedResource<Area>(new ArrayList<Area>() , pageable.getPageNumber(), pageable.getPageSize(),0);
		}

		int start = pageable.getOffset();
		int end = (start + pageable.getPageSize()) > areaSet.size() ? areaSet.size() : (start + pageable.getPageSize());
		int totalPages = (int) Math.ceil((double) areaSet.size() / pageable.getPageSize());

		List<Area> areaList = new ArrayList<Area>(areaSet);
		try {
			PagedResource<Area> areaPagedResource = new PagedResource<Area>(areaList.subList(start, end), pageable.getPageNumber(), pageable.getPageSize(), totalPages);
			return areaPagedResource;
		}
		catch( Exception e){
			return  new PagedResource<Area>(new ArrayList<Area>() , pageable.getPageNumber(), pageable.getPageSize(),0);
		}
	}
}
