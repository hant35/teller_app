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

import vn.fpt.dbp.vccb.core.domain.organization.Country;
import vn.fpt.dbp.vccb.core.domain.organization.QCountry;
import vn.fpt.dbp.vccb.core.domain.organization.repository.CountryRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
public class CountryController {

	@Autowired
	CountryRepository countryRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/country/list", method = RequestMethod.GET, produces = "application/json")
	public List<Country> listCountries(Principal principle) {
		return countryRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/country/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Country> searchCountries(Principal principle, @RequestBody Country request,
			Pageable pageable) {
		QCountry qCountry = QCountry.country;
		BooleanExpression booleanExpression = qCountry.id.isNotNull();
		if (!StringUtils.isEmpty(request.getCode())) {
			booleanExpression = booleanExpression
					.and(qCountry.code.toUpperCase().like(request.getCode().toUpperCase()));
		}
		Page<Country> result = countryRepository.findAll(booleanExpression, pageable);
		return new PagedResource<Country>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}
}
