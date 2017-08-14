package vn.fpt.dbp.vccb.core.domain.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.customer.AccountNoReserved;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;

@Component
public interface AccountNoReservedRepository
		extends JpaRepository<AccountNoReserved, Long>, QueryDslPredicateExecutor<AccountNoReserved>, DBPRepository<AccountNoReserved> {

	@Query(value = "SELECT NVL(MAX(AN.END_NO),'0009999990000') AS CURRENT_NO FROM ACCOUNT_NO_RESERVED AN, WORKFLOW_MODEL WM WHERE AN.ID=WM.ID AND WM.WORKFLOWSTATUS = :workflowStatus AND AN.BRANCH = :branchId ", nativeQuery = true)
	String getCurrentNo(@Param("workflowStatus") String workflowStatus, @Param("branchId") Long branchId);

	public AccountNoReserved findTop1ByBranchAndCurrentNoAndQuantityAndEndNoAndWorkflowStatusOrderByApprovedDateDesc(Branch branch, String currentNo, Integer quantity, String endNo, String workflowStatus);
	
	public AccountNoReserved findTop1ByCurrentNoAndWorkflowStatusOrderByApprovedDateDesc(String currentNo, String workflowstatus);
}
