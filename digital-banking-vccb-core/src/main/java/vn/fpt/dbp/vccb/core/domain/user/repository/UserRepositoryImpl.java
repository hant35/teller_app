package vn.fpt.dbp.vccb.core.domain.user.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;

import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.user.User;
import vn.fpt.util.rest.PagedResource;

public class UserRepositoryImpl implements DBPRepository<User>{
	@PersistenceContext
    private EntityManager em;
	
	public PagedResource<User> searchLastedVersion(User user, Pageable pageable){
		
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		String parUsername, parCustomerName, parWorkFlowStatus;
		String parCifCode, parEmployeeCode;
		Long parBranch;
		Integer parFrom, parTo, totalPages;
		Long totalRows;
		
		parFrom = pageable.getPageNumber() * pageable.getPageSize() + 1;
		parTo = (pageable.getPageNumber() + 1) * pageable.getPageSize() + 1;
		totalPages = 0;
		totalRows = new Long(0);
		
		parUsername = "%";
		parCustomerName = "%";
		parWorkFlowStatus = "%";
		parCifCode = "%";
		parEmployeeCode = "%";
		parBranch = new Long(0);
		
		if (StringUtils.isNotEmpty(user.getUsername())){
			parUsername = user.getUsername();
		}
		if(StringUtils.isNotEmpty(user.getCustomerName())){
			parCustomerName = user.getCustomerName();
		}
		if(StringUtils.isNotEmpty(user.getWorkflowStatus())){
			parWorkFlowStatus = user.getWorkflowStatus();
		}
		if(StringUtils.isNotEmpty(user.getCifCode())){
			parCifCode = user.getCifCode();
		}
		if(StringUtils.isNotEmpty(user.getEmployeeCode())){
			parEmployeeCode = user.getEmployeeCode();
		}
		if(user.getBranch() != null){
			parBranch = user.getBranch().getId();
		}
		//2. Build SQL statement
		
		sql.append(" SELECT * FROM ");
		sql.append(" (SELECT A.*, ROWNUM as ROW_NUM FROM ");
		sql.append(" (SELECT WF.*, US.CIF_CODE, US.CUSTOMER_NAME, US.EFFECTED_DATE, US.EMAIL, US.EMPLOYEE_CODE, US.EMPLOYEE_NAME, ");
		sql.append(" US.EXPIRED_DATE, US.IS_ADMIN, ");
		sql.append(" US.IS_TEMPLATE, US.IS_WORK_ON_CIF, US.LAST_PASSWORD_CHANGED_DATE, US.USER_LEVEL, ");
		sql.append(" US.LOGIN_STATUS, US.PASSWORD, US.PHONE, STATUS, ");
		sql.append(" US.USERNAME, US.BRANCH, US.DEPARTMENT, US.END_TIME, US.START_TIME, US.CURRENT_BRANCH, ");
		sql.append(" MAX(CREATED_DATE) OVER(PARTITION BY WF.ORIGINAL_ID,UPPER(US.USERNAME)) MAX_DATE ");
		sql.append(" FROM VCCB_USER US JOIN WORKFLOW_MODEL WF ON US.ID = WF.ID ");
		if("%".equalsIgnoreCase(parWorkFlowStatus)){
			sql.append(" WHERE WF.WORKFLOWSTATUS <> 'REJECTED' ");
		}else{
			sql.append(" WHERE UPPER(WF.WORKFLOWSTATUS) LIKE (UPPER(:parWorkFlowStatus)) ");
		}
		sql.append(" ORDER BY CREATED_DATE DESC) A ");
		sql.append(" WHERE CREATED_DATE = MAX_DATE ");
		sql.append(" AND UPPER(NVL(A.EMPLOYEE_CODE,' ')) LIKE (UPPER(:parEmployeeCode)) ");
		sql.append(" AND UPPER(NVL(A.CIF_CODE,' ')) LIKE (UPPER(:parCifCode)) ");
		sql.append(" AND UPPER(NVL(A.CUSTOMER_NAME,' ')) LIKE (UPPER(:parCustomerName)) ");
		sql.append(" AND UPPER(NVL(A.USERNAME,' ')) LIKE (UPPER(:parUserName)) ");
		sql.append(" AND NVL(A.BRANCH,0) = DECODE(:parBranch,0,NVL(A.BRANCH,0),:parBranch) ");
		sql.append(" AND UPPER(NVL(WORKFLOWSTATUS,' ')) LIKE (UPPER(:parWorkFlowStatus)) ");
		sql.append(" ) WHERE ROW_NUM >= :parFrom AND ROW_NUM < :parTo");
				
		sqlCount.append(" SELECT COUNT(ID) FROM ");
		sqlCount.append(" (SELECT A.*, ROWNUM as ROW_NUM FROM ");
		sqlCount.append(" (SELECT WF.*, US.CIF_CODE, US.CUSTOMER_NAME, US.EFFECTED_DATE, US.EMAIL, US.EMPLOYEE_CODE, US.EMPLOYEE_NAME, ");
		sqlCount.append(" US.EXPIRED_DATE, US.IS_ADMIN, ");
		sqlCount.append(" US.IS_TEMPLATE, US.IS_WORK_ON_CIF, US.LAST_PASSWORD_CHANGED_DATE, US.USER_LEVEL, ");
		sqlCount.append(" US.LOGIN_STATUS, US.PASSWORD, US.PHONE,STATUS, ");
		sqlCount.append(" US.USERNAME, US.BRANCH, US.DEPARTMENT, US.END_TIME, US.START_TIME, US.CURRENT_BRANCH, ");
		sqlCount.append(" MAX(CREATED_DATE) OVER(PARTITION BY WF.ORIGINAL_ID, UPPER(US.USERNAME)) MAX_DATE ");
		sqlCount.append(" FROM VCCB_USER US JOIN WORKFLOW_MODEL WF ON US.ID = WF.ID ");
		if("%".equalsIgnoreCase(parWorkFlowStatus)){
			sqlCount.append(" WHERE WF.WORKFLOWSTATUS <> 'REJECTED' ");
		}else{
			sqlCount.append(" WHERE UPPER(WF.WORKFLOWSTATUS) LIKE (UPPER(:parWorkFlowStatus)) ");
		}
		sqlCount.append(" ORDER BY CREATED_DATE DESC) A ");
		sqlCount.append(" WHERE CREATED_DATE = MAX_DATE ");
		sqlCount.append(" AND UPPER(NVL(A.EMPLOYEE_CODE,' ')) LIKE (UPPER(:parEmployeeCode)) ");
		sqlCount.append(" AND UPPER(NVL(A.CIF_CODE,' ')) LIKE (UPPER(:parCifCode)) ");
		sqlCount.append(" AND UPPER(NVL(A.CUSTOMER_NAME,' ')) LIKE (UPPER(:parCustomerName)) ");
		sqlCount.append(" AND UPPER(NVL(A.USERNAME,' ')) LIKE (UPPER(:parUserName)) ");
		sqlCount.append(" AND NVL(A.BRANCH,0) = DECODE(:parBranch,0,NVL(A.BRANCH,0),:parBranch) ");
		sqlCount.append(" AND UPPER(NVL(WORKFLOWSTATUS,' ')) LIKE (UPPER(:parWorkFlowStatus)) ");
		sqlCount.append(" ) ");
		
		Query query = em.createNativeQuery(sql.toString(),User.class);
		Query queryCount = em.createNativeQuery(sqlCount.toString());
		
		//3. Set parameter for SQL statement
		query.setParameter("parEmployeeCode", parEmployeeCode);
		query.setParameter("parCifCode", parCifCode);
		query.setParameter("parCustomerName", parCustomerName);
		query.setParameter("parUserName", parUsername);
		query.setParameter("parBranch", parBranch);
		query.setParameter("parWorkFlowStatus", parWorkFlowStatus);
		query.setParameter("parFrom", parFrom);
		query.setParameter("parTo", parTo);
		
		queryCount.setParameter("parEmployeeCode", parEmployeeCode);
		queryCount.setParameter("parCifCode", parCifCode);
		queryCount.setParameter("parCustomerName", parCustomerName);
		queryCount.setParameter("parUserName", parUsername);
		queryCount.setParameter("parBranch", parBranch);
		queryCount.setParameter("parWorkFlowStatus", parWorkFlowStatus);
		
		//4. Execute SQL statement
		List<User> users = query.getResultList();
		List<BigDecimal> lsInt = queryCount.getResultList();
		if (lsInt != null){
			totalRows = Long.parseLong(lsInt.get(0).toString());
			totalPages = (int) Math.ceil((double) totalRows / pageable.getPageSize());
		}
		
		PagedResource<User> result = new PagedResource<User>(users,pageable.getPageNumber(),pageable.getPageSize(),totalPages,totalRows);
		
		return result;
	}

}
