package vn.fpt.dbp.vccb.core.domain.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerGroup;

@Component
public interface CustomerGroupRepository
		extends JpaRepository<CustomerGroup, Long>, QueryDslPredicateExecutor<CustomerGroup> {
	public CustomerGroup findByCode(String code);
}
