package vn.fpt.dbp.vccb.core.domain.interest.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.interest.Interest;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.rest.PagedResource;

public class InterestRepositoryImpl implements DBPRepository<Interest> {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	protected InterestRepository interestRepository;

	public PagedResource<Interest> searchLastedVersion(Interest interest, Pageable pageable) {
		PagedResource<Interest> res = new PagedResource<Interest>();
		List<Interest> interests = new ArrayList<Interest>();

		StringBuffer sqlQuery = new StringBuffer("");
		StringBuffer sqlQueryCnt = new StringBuffer();
		StringBuffer sqlBody = new StringBuffer();
		StringBuffer tmpStr = new StringBuffer();
		String groupKey = " PRODUCT,AREA,BRANCH,CURRENCY,TIME_RATE,EFFECT_DATE ";
		int recordFrom = 1;
		int recordTo = 20;
		int totalPages = 0;
		Long totalRows;

		if (interest == null) {
			return res;
		}

		// Chuẩn bị thông tin phân trang
		recordFrom = pageable.getPageNumber() * pageable.getPageSize() + 1;
		recordTo = (pageable.getPageNumber() + 1) * pageable.getPageSize() + 1;
		totalRows = new Long(0);

		// Header
		sqlQuery.append("SELECT ID FROM ( ");
		sqlQuery.append(" SELECT ID, ROW_NUMBER() OVER (ORDER BY ID desc) RNUM ");
		sqlQueryCnt.append("SELECT COUNT(ID) AS CNT ");

		// Join table
		sqlBody.append(" FROM (SELECT ID,ROW_NUMBER() OVER (PARTITION BY ");
		sqlBody.append(groupKey);
		sqlBody.append(" ORDER BY CREATED_DATE desc) as NUM ");
		sqlBody.append(" FROM (select DISTINCT ITS.*,WM.CREATED_DATE ");
		sqlBody.append(" from WORKFLOW_MODEL WM inner join INTEREST ITS ON WM.ID=ITS.ID ");
		// sqlBody.append(" left join INTEREST_PARAMETER ITP ON ITS.ID =
		// ITP.INTEREST ");
		// sqlBody.append(" left join PRODUCT PRD ON ITS.PRODUCT = PRD.ID ");
		sqlBody.append(" LEFT JOIN INTEREST_UPLOAD_FILE ITF ON ITS.INTEREST_UPLOAD_FILE = ITF.ID ");
		sqlBody.append(" WHERE (ITS.INTEREST_UPLOAD_FILE is null ");
		sqlBody.append(
				" OR (ITS.INTEREST_UPLOAD_FILE is not null and WM.WORKFLOWSTATUS = '" + Status.APPROVED + "')) ");

		// Add conditions here
		if (StringUtils.isNotEmpty(interest.getWorkflowStatus())) {
			tmpStr.append(" AND WM.WORKFLOWSTATUS like (:workFlowStatus) ");
			if (Status.SAVE_DRAFT.equalsIgnoreCase(interest.getWorkflowStatus()) && interest.getCreatedBy() != null) {
				tmpStr.append(" AND WM.CREATED_BY = :createBy ");
			}
		} else {
			tmpStr.append(" AND WM.WORKFLOWSTATUS not like '" + Status.REJECTED + "' ");
			if (interest.getCreatedBy() != null) {
				tmpStr.append(" AND (WM.WORKFLOWSTATUS not like '" + Status.SAVE_DRAFT + "' ");
				tmpStr.append(
						"  OR (WM.WORKFLOWSTATUS like '" + Status.SAVE_DRAFT + "' AND WM.CREATED_BY = :createBy )) ");
			}
		}

		if (interest.getProduct() != null) {
			tmpStr.append(" AND ITS.PRODUCT = :productId ");
		}

		if (interest.getCurrency() != null) {
			tmpStr.append(" AND ITS.CURRENCY = :currencyId ");
		}

		if (interest.getTimeRate() != null) {
			tmpStr.append(" AND ITS.TIME_RATE = :timerateId ");
		}

		if (interest.getArea() != null) {
			tmpStr.append(" AND ITS.AREA = :areaId ");
		}

		if (interest.getBranch() != null) {
			tmpStr.append(" AND ITS.BRANCH = :branchId ");
		}

		if (interest.getEffectDate() != null) {
			tmpStr.append(" AND ITS.EFFECT_DATE = :effectDate ");
		}

		if (interest.getInterestUploadFile() != null) {
			tmpStr.append(" AND UPPER(ITF.FILENAME) like (:fileName) ");
		}

		if (interest.getProcessInstanceId() != null) {
			tmpStr.append(" AND WM.PROCESS_INSTANCE_ID = :processInstanceId ");
		}

		if (interest.getTaskId() != null) {
			tmpStr.append(" AND WM.TASK_ID = :taskId ");
		}

		// add condition to sqlBody
		sqlBody.append(tmpStr.toString());
		sqlQuery.append(sqlBody.toString());
		sqlQueryCnt.append(sqlBody.toString());

		// Finish sql
		sqlQuery.append(" ) ORDER BY ID ) where NUM = 1 ) WHERE RNUM >= :recordFrom AND RNUM < :recordTo ");
		sqlQueryCnt.append(" )) where NUM = 1 ");

		Query query = entityManager.createNativeQuery(sqlQuery.toString());
		Query queryCnt = entityManager.createNativeQuery(sqlQueryCnt.toString());

		// Parst parameter
		if (StringUtils.isNotEmpty(interest.getWorkflowStatus())) {
			// tmpStr.append(" AND WM.WORKFLOWSTATUS like (:workFlowStatus) ");
			query.setParameter("workFlowStatus", interest.getWorkflowStatus());
			queryCnt.setParameter("workFlowStatus", interest.getWorkflowStatus());
			if (Status.SAVE_DRAFT.equalsIgnoreCase(interest.getWorkflowStatus()) && interest.getCreatedBy() != null) {
				// tmpStr.append(" AND WM.CREATE_BY = :createBy ");
				query.setParameter("createBy", interest.getCreatedBy());
				queryCnt.setParameter("createBy", interest.getCreatedBy());
			}
		} else {
			// tmpStr.append(" AND WM.WORKFLOWSTATUS not like 'REJECTED' ");
			if (interest.getCreatedBy() != null) {
				// tmpStr.append(" AND (WM.WORKFLOWSTATUS not like 'SAVE_DRAFT'
				// ");
				// tmpStr.append(" OR (WM.WORKFLOWSTATUS like 'SAVE_DRAFT' AND
				// WM.CREATE_BY = :createBy )) ");
				query.setParameter("createBy", interest.getCreatedBy());
				queryCnt.setParameter("createBy", interest.getCreatedBy());
			}
		}

		if (interest.getProduct() != null) {
			// tmpStr.append(" AND ITS.PRODUCT = :productId ");
			query.setParameter("productId", interest.getProduct().getId());
			queryCnt.setParameter("productId", interest.getProduct().getId());

		}

		if (interest.getCurrency() != null) {
			// tmpStr.append(" AND ITS.CURRENCY = :currencyId ");
			query.setParameter("currencyId", interest.getCurrency().getId());
			queryCnt.setParameter("currencyId", interest.getCurrency().getId());
		}

		if (interest.getTimeRate() != null) {
			// tmpStr.append(" AND ITS.TIME_RATE = :timerateId ");
			query.setParameter("timerateId", interest.getTimeRate().getId());
			queryCnt.setParameter("timerateId", interest.getTimeRate().getId());
		}

		if (interest.getArea() != null) {
			// tmpStr.append(" AND ITS.AREA = :areaId ");
			query.setParameter("areaId", interest.getArea().getId());
			queryCnt.setParameter("areaId", interest.getArea().getId());
		}

		if (interest.getBranch() != null) {
			// tmpStr.append(" AND ITS.BRANCH = :branchId ");
			query.setParameter("branchId", interest.getBranch().getId());
			queryCnt.setParameter("branchId", interest.getBranch().getId());
		}

		if (interest.getEffectDate() != null) {
			// tmpStr.append(" AND ITS.EFFECT_DATE = :effectDate ");
			query.setParameter("effectDate", interest.getEffectDate());
			queryCnt.setParameter("effectDate", interest.getEffectDate());
		}

		if (interest.getInterestUploadFile() != null) {
			// tmpStr.append(" AND ITF.FILENAME like (:fileName) ");
			query.setParameter("fileName", interest.getInterestUploadFile().getFileName().toUpperCase());
			queryCnt.setParameter("fileName", interest.getInterestUploadFile().getFileName().toUpperCase());
		}

		if (interest.getProcessInstanceId() != null) {
			// tmpStr.append(" AND WM.PROCESS_INSTANCE_ID = :processInstanceId
			// ");
			query.setParameter("processInstanceId", interest.getProcessInstanceId());
			queryCnt.setParameter("processInstanceId", interest.getProcessInstanceId());
		}

		if (interest.getTaskId() != null) {
			// tmpStr.append(" AND WM.TASK_ID = :taskId ");
			query.setParameter("taskId", interest.getTaskId());
			queryCnt.setParameter("taskId", interest.getTaskId());
		}
		// sqlQuery.append(" )) where NUM = 1 AND ROWNUM >= :recordFrom AND
		// ROWNUM <= :recordTo ");
		query.setParameter("recordFrom", recordFrom);
		query.setParameter("recordTo", recordTo);

		// 4. Execute SQL statement

		@SuppressWarnings("unchecked")
		List<BigDecimal> interestIdsCnt = queryCnt.getResultList();

		if (interestIdsCnt != null) {
			totalRows = Long.parseLong(interestIdsCnt.get(0).toString());
			totalPages = (int) Math.ceil(totalRows / pageable.getPageSize());
		}
		@SuppressWarnings("unchecked")
		List<BigDecimal> interestIds = query.getResultList();
		if (interestIds != null && !interestIds.isEmpty()) {
			for (BigDecimal id : interestIds) {
				System.out.println("id:" + id);
				Interest its = interestRepository.findOne(id.longValue());
				if (its != null) {
					interests.add(its);
				}
			}
		}

		res = new PagedResource<Interest>(interests, pageable.getPageNumber(), pageable.getPageSize(), totalPages,
				totalRows);

		return res;
	};

}
