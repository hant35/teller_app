package vn.fpt.dbp.vccb.service.reference.rest;

import java.security.Principal;
import java.util.List;

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

import vn.fpt.dbp.vccb.core.domain.organization.City;
import vn.fpt.dbp.vccb.core.domain.organization.District;
import vn.fpt.dbp.vccb.core.domain.organization.QCity;
import vn.fpt.dbp.vccb.core.domain.organization.QDistrict;
import vn.fpt.dbp.vccb.core.domain.organization.QWard;
import vn.fpt.dbp.vccb.core.domain.organization.Ward;
import vn.fpt.dbp.vccb.core.domain.organization.repository.CityRepository;
import vn.fpt.dbp.vccb.core.domain.organization.repository.DistrictRepository;
import vn.fpt.dbp.vccb.core.domain.organization.repository.WardRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
@EnableTransactionManagement(proxyTargetClass = true)
public class AddressController {

	@Value("${dbp.api-gateway-url}")
	private String apiGatewayUrl;// = "http://10.86.202.223:8080";

	@Value("${spring.application.name}")
	private String serviceName;

	@Autowired
	CityRepository cityRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	WardRepository wardRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/address/city/list", method = RequestMethod.GET, produces = "application/json")
	public List<City> listCities(Principal principle) {
		return cityRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/address/district/list", method = RequestMethod.GET, produces = "application/json")
	public List<District> listDistricts(Principal principle) {
		return districtRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/address/ward/list", method = RequestMethod.GET, produces = "application/json")
	public List<Ward> listWards(Principal principle) {
		return wardRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/address/city/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<City> searchCities(Principal principle, @RequestBody City request, Pageable pageable) {
		QCity qCity = QCity.city;
		BooleanExpression booleanExpression = qCity.id.isNotNull();
		if (request.getCountry() != null) {
			booleanExpression = booleanExpression.and(qCity.country.eq(request.getCountry()));
		}
		Page<City> result = cityRepository.findAll(booleanExpression, pageable);
		return new PagedResource<City>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/address/district/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<District> searchDistricts(Principal principle, @RequestBody District request,
			Pageable pageable) {
		QDistrict qDistrict = QDistrict.district;
		BooleanExpression booleanExpression = qDistrict.id.isNotNull();
		if (request.getCity() != null) {
			booleanExpression = booleanExpression.and(qDistrict.city.eq(request.getCity()));
		}
		Page<District> result = districtRepository.findAll(booleanExpression, pageable);
		return new PagedResource<District>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/address/ward/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Ward> searchWards(Principal principle, @RequestBody Ward request, Pageable pageable) {
		QWard qWard = QWard.ward;
		BooleanExpression booleanExpression = qWard.id.isNotNull();
		if (request.getDistrict() != null) {
			booleanExpression = booleanExpression.and(qWard.district.eq(request.getDistrict()));
		}
		Page<Ward> result = wardRepository.findAll(booleanExpression, pageable);
		return new PagedResource<Ward>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}

}
