package vn.fpt.dbp.vccb.core.domain.role.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;

import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.role.Role;
import vn.fpt.util.rest.PagedResource;

public class RoleRepositoryImpl implements DBPRepository<Role>{
	@PersistenceContext
    private EntityManager em;
	
	public PagedResource<Role> searchLastedVersion(Role role, Pageable pageable){
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlCount = new StringBuilder();
		String parCode,parName,parWorkFlowStatus,parCreatedBy;
		Integer parFrom, parTo, totalPages;
		Long totalRows;
		
		//1. Create value for parameters from input data
		parCode = "%";
		parName = "%";
		parWorkFlowStatus = "%";
		parCreatedBy = " ";
		if(StringUtils.isNotEmpty(role.getCode())){
			parCode = role.getCode();
		}
		if(StringUtils.isNotEmpty(role.getName())){
			parName = role.getName();
		}
		if(StringUtils.isNotEmpty(role.getWorkflowStatus())){
			parWorkFlowStatus = role.getWorkflowStatus();
		}
		if(role.getCreatedBy() != null && role.getCreatedBy().getId() != null){
			parCreatedBy = role.getCreatedBy().getId().toString();
		}
		parFrom = pageable.getPageNumber() * pageable.getPageSize() + 1;
		parTo = (pageable.getPageNumber() + 1) * pageable.getPageSize() + 1;
		totalPages = 0;
		totalRows = new Long(0);
		
		//2. Build SQL statement
		
		sql.append(" SELECT * FROM ");
		sql.append(" (SELECT A.*, ROWNUM as ROW_NUM FROM ");
		sql.append(" (SELECT WF.*, R.CODE, R.NAME, R.STATUS,R.DESCRIPTION, R.IS_ADMIN, MAX(CREATED_DATE) OVER(PARTITION BY WF.ORIGINAL_ID,UPPER(R.CODE)) MAX_DATE ");
		sql.append(" FROM VCCB_ROLE R JOIN WORKFLOW_MODEL WF ON R.ID = WF.ID ");
		
		if("%".equalsIgnoreCase(parWorkFlowStatus)){
			sql.append(" WHERE WF.WORKFLOWSTATUS <> 'REJECTED' ");
			sql.append(" AND DECODE(WF.WORKFLOWSTATUS,'DRAFT',TO_CHAR(NVL(WF.CREATED_BY,0)),WF.WORKFLOWSTATUS) = DECODE(WF.WORKFLOWSTATUS,'DRAFT',:parCreatedBy,WF.WORKFLOWSTATUS) ");
		}else{
			sql.append(" WHERE WF.WORKFLOWSTATUS LIKE (:parWorkFlowStatus) ");
			sql.append(" AND DECODE(WF.WORKFLOWSTATUS,'DRAFT',TO_CHAR(NVL(WF.CREATED_BY,0)),WF.WORKFLOWSTATUS) = DECODE(WF.WORKFLOWSTATUS,'DRAFT',:parCreatedBy,WF.WORKFLOWSTATUS) ");
		}
		sql.append(" ORDER BY CREATED_DATE DESC) A ");
		sql.append(" WHERE CREATED_DATE = MAX_DATE ");
		sql.append(" AND UPPER(NVL(CODE,' ')) LIKE (UPPER(:parCode)) ");
		sql.append(" AND UPPER(NVL(NAME,' ')) LIKE (UPPER(:parName)) ");
		sql.append(" AND NVL(WORKFLOWSTATUS,' ') LIKE (:parWorkFlowStatus) ");
		sql.append(" ) WHERE ROW_NUM >= :parFrom AND ROW_NUM < :parTo");
		
		sqlCount.append(" SELECT COUNT(ID) FROM ");
		sqlCount.append(" (SELECT A.*, ROWNUM as ROW_NUM FROM ");
		sqlCount.append(" (SELECT WF.*, R.CODE, R.NAME, R.STATUS,R.DESCRIPTION, R.IS_ADMIN, MAX(CREATED_DATE) OVER(PARTITION BY WF.ORIGINAL_ID,UPPER(R.CODE)) MAX_DATE ");
		sqlCount.append(" FROM VCCB_ROLE R JOIN WORKFLOW_MODEL WF ON R.ID = WF.ID ");
		
		if("%".equalsIgnoreCase(parWorkFlowStatus)){
			sqlCount.append(" WHERE WF.WORKFLOWSTATUS <> 'REJECTED' ");
			sqlCount.append(" AND DECODE(WF.WORKFLOWSTATUS,'DRAFT',TO_CHAR(NVL(WF.CREATED_BY,0)),WF.WORKFLOWSTATUS) = DECODE(WF.WORKFLOWSTATUS,'DRAFT',:parCreatedBy,WF.WORKFLOWSTATUS) ");
		}else{
			sqlCount.append(" WHERE WF.WORKFLOWSTATUS LIKE (:parWorkFlowStatus) ");
			sqlCount.append(" AND DECODE(WF.WORKFLOWSTATUS,'DRAFT',TO_CHAR(NVL(WF.CREATED_BY,0)),WF.WORKFLOWSTATUS) = DECODE(WF.WORKFLOWSTATUS,'DRAFT',:parCreatedBy,WF.WORKFLOWSTATUS) ");
		}
		sqlCount.append(" ORDER BY CREATED_DATE DESC) A ");
		sqlCount.append(" WHERE CREATED_DATE = MAX_DATE ");
		sqlCount.append(" AND UPPER(NVL(CODE,' ')) LIKE (UPPER(:parCode)) ");
		sqlCount.append(" AND UPPER(NVL(NAME,' ')) LIKE (UPPER(:parName)) ");
		sqlCount.append(" AND NVL(WORKFLOWSTATUS,' ') LIKE (:parWorkFlowStatus) ");
		sqlCount.append(" )");
		
		Query query = em.createNativeQuery(sql.toString(),Role.class);
		Query queryCount = em.createNativeQuery(sqlCount.toString());
		
		//3. Set parameter for SQL statement
		
		query.setParameter("parCode", parCode);
		query.setParameter("parName", parName);
		query.setParameter("parWorkFlowStatus", parWorkFlowStatus);
		query.setParameter("parFrom", parFrom);
		query.setParameter("parTo", parTo);
		query.setParameter("parCreatedBy", parCreatedBy);
		
		queryCount.setParameter("parCode", parCode);
		queryCount.setParameter("parName", parName);
		queryCount.setParameter("parWorkFlowStatus", parWorkFlowStatus);
		queryCount.setParameter("parCreatedBy", parCreatedBy);
		
		//4. Execute SQL statement
		List<Role> roles = query.getResultList();
		List<BigDecimal> lsInt = queryCount.getResultList();
		if (lsInt != null){
			totalRows = Long.parseLong(lsInt.get(0).toString());
			totalPages = (int) Math.ceil((double) totalRows / pageable.getPageSize());
		}
		
		PagedResource<Role> result = new PagedResource<Role>(roles,pageable.getPageNumber(),pageable.getPageSize(),totalPages,totalRows);
	
		return result;
	}
}
