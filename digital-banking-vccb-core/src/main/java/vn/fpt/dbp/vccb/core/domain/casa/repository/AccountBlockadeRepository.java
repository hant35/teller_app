package vn.fpt.dbp.vccb.core.domain.casa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.casa.AccountBlockade;

@Component
public interface AccountBlockadeRepository
		extends JpaRepository<AccountBlockade, Long>, QueryDslPredicateExecutor<AccountBlockade> {

}
