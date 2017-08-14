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

import vn.fpt.dbp.vccb.core.domain.customer.BusinessSector;
import vn.fpt.dbp.vccb.core.domain.customer.QBusinessSector;
import vn.fpt.dbp.vccb.core.domain.customer.repository.BusinessSectorRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
public class BusinessSectorController {

	@Autowired
	BusinessSectorRepository businessSectorRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/businesssector/list", method = RequestMethod.GET, produces = "application/json")
	public List<BusinessSector> listBusinessSectors(Principal principle) {
		return businessSectorRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/businesssector/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<BusinessSector> searchBusinessSectors(Principal principle, @RequestBody BusinessSector request,
			Pageable pageable) {
		QBusinessSector qBusinessSector = QBusinessSector.businessSector;
		BooleanExpression booleanExpression = qBusinessSector.id.isNotNull();
		if (!StringUtils.isEmpty(request.getCode())) {
			booleanExpression.and(qBusinessSector.code.toUpperCase().like(request.getCode().toUpperCase()));
		}
		Page<BusinessSector> result = businessSectorRepository.findAll(booleanExpression, pageable);
		return new PagedResource<BusinessSector>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages());
	}
}
