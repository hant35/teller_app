package vn.fpt.dbp.vccb.core.domain.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.organization.Area;

@Component
public interface AreaRepository extends JpaRepository<Area, Long>, QueryDslPredicateExecutor<Area> {
	public Area findByCode(String code);

}