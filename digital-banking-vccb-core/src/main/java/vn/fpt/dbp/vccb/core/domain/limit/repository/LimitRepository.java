package vn.fpt.dbp.vccb.core.domain.limit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.limit.Limit;

@Component
public interface LimitRepository extends JpaRepository<Limit, Long>, QueryDslPredicateExecutor<Limit>, DBPRepository<Limit>{

	public Limit findByCode(String code);
	public List<Limit> findByName(String name);
	public Limit findTop1ByCodeAndWorkflowStatusOrderByApprovedDateAsc(String code,String workflowStatus);
	public Limit findTop1ByCodeAndWorkflowStatusOrderByApprovedDateDesc(String code,String workflowStatus);
	public Limit findTop1ByCodeOrderByApprovedDateDesc(String code);
	
}
