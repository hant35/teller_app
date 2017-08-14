package vn.fpt.dbp.vccb.core.domain.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.customer.CustomerType;

@Component
public interface CustomerTypeRepository
		extends JpaRepository<CustomerType, Long>, QueryDslPredicateExecutor<CustomerType> {
	public CustomerType findByCode(String code);
	public CustomerType findByCodeIgnoreCase(String code);
}
