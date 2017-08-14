package vn.fpt.dbp.vccb.core.domain.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.role.Role;


@Component
public interface RoleRepository extends JpaRepository<Role, Long>, QueryDslPredicateExecutor<Role>, DBPRepository<Role> {
 
	public Role findByCode(String code);
	public List<Role> findByName(String name);
	public Role findTop1ByCodeAndWorkflowStatusOrderByApprovedDateAsc(String code,String workflowStatus);
	public Role findTop1ByCodeAndWorkflowStatusOrderByApprovedDateDesc(String code,String workflowStatus);
}