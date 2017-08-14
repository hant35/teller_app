package vn.fpt.dbp.vccb.core.domain.limit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.limit.LimitGroup;

@Component
public interface LimitGroupRepository extends JpaRepository<LimitGroup, Long>, QueryDslPredicateExecutor<LimitGroup>, DBPRepository<LimitGroup>{
	public LimitGroup findByCode(String code);
	public List<LimitGroup> findByName(String name);
	public LimitGroup findTop1ByCodeAndWorkflowStatusOrderByApprovedDateAsc(String code,String workflowStatus);
	public LimitGroup findTop1ByCodeAndWorkflowStatusOrderByApprovedDateDesc(String code,String workflowStatus);

}
