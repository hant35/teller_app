package vn.fpt.dbp.vccb.core.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.user.RestrictUserCurrency;

@Component
public interface RestrictUserCurrencyRepository extends JpaRepository<RestrictUserCurrency, Long>, QueryDslPredicateExecutor<RestrictUserCurrency>{

}
