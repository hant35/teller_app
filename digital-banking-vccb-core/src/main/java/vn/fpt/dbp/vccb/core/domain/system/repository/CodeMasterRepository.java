package vn.fpt.dbp.vccb.core.domain.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.system.CodeMaster;

@Component
public interface CodeMasterRepository extends JpaRepository<CodeMaster, Long>, QueryDslPredicateExecutor<CodeMaster> {
	public List<CodeMaster> findByCodeIgnoreCaseOrderBySortNoAsc(String code);

	public List<CodeMaster> findByCodeIgnoreCaseAndIsDeleteFalseOrderBySortNoAsc(String code);

}
