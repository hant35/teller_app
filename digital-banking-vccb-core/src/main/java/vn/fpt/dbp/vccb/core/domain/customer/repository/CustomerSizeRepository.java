package vn.fpt.dbp.vccb.core.domain.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerSize;

@Component
public interface CustomerSizeRepository
		extends JpaRepository<CustomerSize, Long>, QueryDslPredicateExecutor<CustomerSize> {
	public CustomerSize findByCode(String code);
}
