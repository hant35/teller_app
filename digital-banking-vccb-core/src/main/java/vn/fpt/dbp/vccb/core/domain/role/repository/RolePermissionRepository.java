package vn.fpt.dbp.vccb.core.domain.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.role.RolePermission;

@Component
public interface RolePermissionRepository  extends JpaRepository<RolePermission, Long>, QueryDslPredicateExecutor<RolePermission>{
	

}
