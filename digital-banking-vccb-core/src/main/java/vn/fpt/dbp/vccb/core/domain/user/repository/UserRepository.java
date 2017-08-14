package vn.fpt.dbp.vccb.core.domain.user.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.user.User;

@Component
public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User>, DBPRepository<User>{

	public User findByUsernameAndWorkflowStatus(String username,String workflowStatus );
	public User findByUsernameAndEmployeeCode(String username,String employeeCode );
	public Page<User> findByUsername(String username,Pageable pageable );
	public List<User> findByUsername(String username);
	public Page<User> findByEmployeeCode(String employeeCode,Pageable pageable );
	public List<User> findByEmployeeCode(String employeeCode);
	
	public User findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateAsc(String username,String workflowStatus);
	public User findTop1ByUsernameAndWorkflowStatusOrderByApprovedDateDesc(String username,String workflowStatus);
}
