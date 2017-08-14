package vn.fpt.dbp.vccb.core.domain.customer.repository;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.customer.AccountNoReserved;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.rest.PagedResource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by NghiaPV on 7/7/2017.
 */
public class AccountNoReservedRepositoryImpl implements DBPRepository<AccountNoReserved> {

    @PersistenceContext
    private EntityManager em;

    public PagedResource<AccountNoReserved> searchLastedVersion(AccountNoReserved accountNoReserved, Pageable pageable){
        StringBuilder sql = new StringBuilder();
        StringBuilder sqlCount = new StringBuilder();
        StringBuilder sqlBody = new StringBuilder();

        Integer parFrom, parTo, totalPages;
        Long totalRows;

        //1. Create value for parameters from input data
        parFrom = pageable.getPageNumber() * pageable.getPageSize() + 1;
        parTo = (pageable.getPageNumber() + 1) * pageable.getPageSize() + 1;
        totalPages = 0;
        totalRows = new Long(0);
        //2. Build SQL statement
        //Header
        sql.append(" SELECT * FROM ");
        sqlCount.append(" SELECT COUNT(ID) FROM ");
        sqlBody.append(" (SELECT A.*, ROWNUM as ROW_NUM FROM ");
        sqlBody.append(" (SELECT  ANR.BRANCH, WF.CURRENT_NO, WF.QUANTITY, WF.END_NO, WF.*, ");
        sqlBody.append("  MAX(CREATED_DATE) OVER(PARTITION BY ORIGINAL_ID, BRANCH, CURRENT_NO, QUANTITY, END_NO) MAX_DATE" );
        //Join
        sqlBody.append(" FROM ACCOUNT_NO_RESERVED ANR JOIN WORKFLOW_MODEL WF ON ANR.ID = WF.ID ");
        //Loại bỏ rejected row cho trường hợp search all.
        if(StringUtils.isEmpty( accountNoReserved.getWorkflowStatus())){
            sqlBody.append(" WHERE WORKFLOWSTATUS NOT LIKE 'REJECTED'");
            sqlBody.append(" AND CREATED_BY = DECODE (WORKFLOWSTATUS, 'DRAFT', :parCreatedById, CREATED_BY) ");
        }else{
            sqlBody.append(" WHERE WORKFLOWSTATUS LIKE (:parWorkFlowStatus)  ");

            //Thêm nhánh trường hợp lọc save_draft row cho login user.
            //Trường hợp login user là null sẽ trả về kết quả rỗng.
            if(Status.SAVE_DRAFT.equalsIgnoreCase(accountNoReserved.getWorkflowStatus())){
                sqlBody.append(" AND CREATED_BY = :parCreatedById  ");
            }
        }
        sqlBody.append(" ORDER BY CREATED_DATE DESC) A ");
        //Condition
        sqlBody.append(" WHERE CREATED_DATE = MAX_DATE  ");
        if(accountNoReserved.getBranch() != null){
            sqlBody.append(" AND BRANCH = (:parBranchId)");
        }
        if(StringUtils.isNotEmpty(accountNoReserved.getCurrentNo())){
            sqlBody.append(" AND upper(CURRENT_NO) LIKE upper((:parCurrentNo))  ");
        }

        if(accountNoReserved.getQuantity() != null){
            sqlBody.append(" AND QUANTITY = (:parQuantity)  ");
        }

        if(accountNoReserved.getEndNo() != null){
            sqlBody.append(" AND upper(END_NO) LIKE upper ((:parEndNo))  ");
        }

        sql.append(sqlBody);
        sql.append(" ) WHERE ROW_NUM >= :parFrom AND ROW_NUM < :parTo");

        sqlCount.append(sqlBody);
        sqlCount.append("  )");

        Query query = em.createNativeQuery(sql.toString(), AccountNoReserved.class);
        Query queryCount = em.createNativeQuery(sqlCount.toString());

        //3. Set parameter for SQL statement
        if(accountNoReserved.getBranch() != null){
            query.setParameter("parBranchId", accountNoReserved.getBranch().getId());
            queryCount.setParameter("parBranchId", accountNoReserved.getBranch().getId());
        }
        if(StringUtils.isNotEmpty(accountNoReserved.getCurrentNo())){
            query.setParameter("parCurrentNo", accountNoReserved.getCurrentNo());
            queryCount.setParameter("parCurrentNo", accountNoReserved.getCurrentNo());
        }
        if(accountNoReserved.getQuantity() != null){
            query.setParameter("parQuantity", accountNoReserved.getQuantity());
            queryCount.setParameter("parQuantity", accountNoReserved.getQuantity());
        }
        if(StringUtils.isNotEmpty(accountNoReserved.getEndNo())){
            query.setParameter("parEndNo", accountNoReserved.getEndNo());
            queryCount.setParameter("parEndNo", accountNoReserved.getEndNo());
        }

        query.setParameter("parFrom", parFrom);
        query.setParameter("parTo", parTo);

        //4. Execute SQL statement
        List<AccountNoReserved> accountNoReservedList = query.getResultList();
        List<BigDecimal> lsInt = queryCount.getResultList();
        if (lsInt != null){
            totalRows = Long.parseLong(lsInt.get(0).toString());
            totalPages = (int) Math.ceil((double) totalRows / pageable.getPageSize());
        }

        PagedResource<AccountNoReserved> result = new PagedResource<AccountNoReserved>(accountNoReservedList,pageable.getPageNumber(),pageable.getPageSize(),totalPages, totalRows);

        return result;
    }

}
