package vn.fpt.dbp.vccb.core.domain.casa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.casa.ServiceType;

@Component
public interface ServiceTypeRepository
		extends JpaRepository<ServiceType, Long>, QueryDslPredicateExecutor<ServiceType> {

}
