package vn.fpt.dbp.vccb.service.reference.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.dsl.BooleanExpression;

import vn.fpt.dbp.vccb.core.domain.organization.Language;
import vn.fpt.dbp.vccb.core.domain.organization.QLanguage;
import vn.fpt.dbp.vccb.core.domain.organization.repository.LanguageRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
public class LanguageController {

	@Autowired
	LanguageRepository languageRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/language/list", method = RequestMethod.GET, produces = "application/json")
	public List<Language> listLanguages(Principal principle) {
		return languageRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/language/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<Language> searchLanguages(Principal principle, @RequestBody Language request,
			Pageable pageable) {
		QLanguage qLanguage = QLanguage.language;
		BooleanExpression booleanExpression = qLanguage.id.isNotNull();
		if (!StringUtils.isEmpty(request.getCode())) {
			booleanExpression = booleanExpression
					.and(qLanguage.code.toUpperCase().like(request.getCode().toUpperCase()));
		}
		Page<Language> result = languageRepository.findAll(booleanExpression, pageable);
		return new PagedResource<Language>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(), result.getTotalElements());
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/language/detail/{id}", method = RequestMethod.GET, produces = "application/json")
	public Language languageDetail(Principal principle, @PathVariable String id) {
		long lId;
		try {
			lId = Long.parseLong(id);
		} catch (Exception e) {
			lId = 0L;
		}
		Language language = languageRepository.findOne(lId);

		return language;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/language/detailbycode/{code}", method = RequestMethod.GET, produces = "application/json")
	public Language languageDetailByCode(Principal principle, @PathVariable String code) {

		Language language = languageRepository.findByCodeEqualsIgnoreCase(code);

		return language;
	}
}
