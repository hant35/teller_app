package vn.fpt.dbp.vccb.core.domain.casa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.casa.AccountBlockadeRaise;

@Component
public interface AccountBlockadeRaiseRepository
		extends JpaRepository<AccountBlockadeRaise, Long>, QueryDslPredicateExecutor<AccountBlockadeRaise> {

}
