package vn.fpt.dbp.vccb.core.domain.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.organization.Ward;

@Component
public interface WardRepository extends JpaRepository<Ward, Long>, QueryDslPredicateExecutor<Ward> {
	public Ward findByName(String name);
}
