package vn.fpt.dbp.vccb.core.domain.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.customer.ReferralUser;

@Component
public interface ReferralUserRepository
		extends JpaRepository<ReferralUser, Long>, QueryDslPredicateExecutor<ReferralUser> {
	public ReferralUser findByCode(String code);

	public List<ReferralUser> findByName(String name);
}
