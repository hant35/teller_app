package vn.fpt.dbp.vccb.core.domain.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.organization.Department;

@Component
public interface DepartmentRepository extends JpaRepository<Department, Long>, QueryDslPredicateExecutor<Department>{
	public Department findByCode(String code);

}
