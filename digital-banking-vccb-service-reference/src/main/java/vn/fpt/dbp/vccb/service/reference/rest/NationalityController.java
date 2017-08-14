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

import vn.fpt.dbp.vccb.core.domain.organization.Nationality;
import vn.fpt.dbp.vccb.core.domain.organization.QNationality;
import vn.fpt.dbp.vccb.core.domain.organization.repository.NationalityRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
public class NationalityController {

	@Autowired
	NationalityRepository nationalityRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/nationality/list", method = RequestMethod.GET, produces = "application/json")
	public List<Nationality> listNationalities(Principal principle) {
		return nationalityRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/nationality/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Nationality> searchNationalities(Principal principle, @RequestBody Nationality request,
			Pageable pageable) {
		QNationality qNationality = QNationality.nationality;
		BooleanExpression booleanExpression = qNationality.id.isNotNull();
		if (!StringUtils.isEmpty(request.getCode())) {
			booleanExpression = booleanExpression
					.and(qNationality.code.toUpperCase().like(request.getCode().toUpperCase()));
		}
		Page<Nationality> result = nationalityRepository.findAll(booleanExpression, pageable);
		return new PagedResource<Nationality>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}
}
