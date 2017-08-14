package vn.fpt.dbp.vccb.core.domain.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.customer.BusinessSector;

@Component
public interface BusinessSectorRepository
		extends JpaRepository<BusinessSector, Long>, QueryDslPredicateExecutor<BusinessSector> {
	public BusinessSector findByCode(String code);
}
