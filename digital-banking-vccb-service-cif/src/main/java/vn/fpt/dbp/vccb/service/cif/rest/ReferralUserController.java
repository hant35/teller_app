package vn.fpt.dbp.vccb.service.cif.rest;

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

import vn.fpt.dbp.vccb.core.domain.customer.QReferralUser;
import vn.fpt.dbp.vccb.core.domain.customer.ReferralUser;
import vn.fpt.dbp.vccb.core.domain.customer.repository.ReferralUserRepository;
import vn.fpt.util.rest.PagedResource;

@RestController
@Component
public class ReferralUserController {

	@Autowired
	ReferralUserRepository referralUserRepository;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/referraluser/list", method = RequestMethod.GET, produces = "application/json")
	public List<ReferralUser> listReferralUsers(Principal principle) {
		return referralUserRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/referraluser/search", method = RequestMethod.POST, produces = "application/json")
	public PagedResource<ReferralUser> searchReferralUsers(Principal principle, @RequestBody ReferralUser request,
			Pageable pageable) {
		QReferralUser qReferralUser = QReferralUser.referralUser;
		BooleanExpression booleanExpression = qReferralUser.id.isNotNull();
		if (!StringUtils.isEmpty(request.getCode())) {
			booleanExpression.and(qReferralUser.code.toUpperCase().like(request.getCode().toUpperCase()));
		}
		if (!StringUtils.isEmpty(request.getName())) {
			booleanExpression.and(qReferralUser.name.toUpperCase().like(request.getName().toUpperCase()));
		}
		if(!StringUtils.isEmpty(request.getLegalDocsNumber())){
			booleanExpression.and(qReferralUser.legalDocsNumber.toUpperCase().like(request.getLegalDocsNumber().toUpperCase()));
		}
		Page<ReferralUser> result = referralUserRepository.findAll(booleanExpression, pageable);
		return new PagedResource<ReferralUser>(result.getContent(), result.getNumber(), result.getSize(),
				result.getTotalPages(),result.getTotalElements());
	}
}
