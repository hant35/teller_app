package vn.fpt.dbp.vccb.core.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.product.AccountClass;

@Component
public interface AccountClassRepository
		extends JpaRepository<AccountClass, Long>, QueryDslPredicateExecutor<AccountClass> {
	public AccountClass findByCode(String code);

}