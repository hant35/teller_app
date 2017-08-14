package vn.fpt.dbp.vccb.core.domain.customer.repository;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.customer.Customer;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.rest.PagedResource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by T450 on 5/25/2017.
 */
public class CustomerRepositoryImpl implements DBPRepository<Customer>
{
    @PersistenceContext
    private EntityManager em;

    public PagedResource<Customer> searchLastedVersion(Customer customer, Pageable pageable){
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
        sqlBody.append(" (SELECT WF.APPROVED_DATE, WF.ASSIGN_GROUP, WF.ASSIGNED_DATE, WF.CREATED_DATE, WF.ORIGINAL_ID, ");
        sqlBody.append("  WF.PROCESS_DEPLOYMENT_ID, WF.PROCESS_INSTANCE_ID, WF.REFERENCE_CODE, WF.TASK_ID, ");
        sqlBody.append("  WF.WORKFLOWSTATUS, WF.APPROVED_BY, WF.ASSIGNEE, WF.CREATED_BY, CM.*, ");
        sqlBody.append("  MAX(CREATED_DATE) OVER(PARTITION BY ORIGINAL_ID, CODE) MAX_DATE" );
        //Join
        sqlBody.append(" FROM CUSTOMER CM JOIN WORKFLOW_MODEL WF ON CM.ID = WF.ID ");
        //Loại bỏ rejected row cho trường hợp search all.
        if(StringUtils.isEmpty( customer.getWorkflowStatus())){
            sqlBody.append(" WHERE WORKFLOWSTATUS NOT LIKE 'REJECTED'");
            sqlBody.append(" AND CREATED_BY = DECODE (WORKFLOWSTATUS, 'DRAFT', :parCreatedById, CREATED_BY) ");
        }else{
            sqlBody.append(" WHERE WORKFLOWSTATUS LIKE (:parWorkFlowStatus)  ");

            //Thêm nhánh trường hợp lọc save_draft row cho login user.
            //Trường hợp login user là null sẽ trả về kết quả rỗng.
            if(Status.SAVE_DRAFT.equalsIgnoreCase(customer.getWorkflowStatus())){
                sqlBody.append(" AND CREATED_BY = :parCreatedById  ");
            }
        }
        sqlBody.append(" ORDER BY CREATED_DATE DESC) A ");
        //Condition
        sqlBody.append(" WHERE CREATED_DATE = MAX_DATE  ");
        if(customer.getCustomerType() != null){
            sqlBody.append(" AND CUSTOMER_TYPE = (:parCustomerTypeId)");
        }
        if(StringUtils.isNotEmpty(customer.getFullName())){
            sqlBody.append(" AND upper(FULL_NAME) LIKE upper((:parFullName))  ");
        }
        if(StringUtils.isNotEmpty(customer.getLegalDocsNumber())){
            sqlBody.append(" AND upper(LEGAL_DOCS_NUMBER) LIKE upper((:parLegalDocsNumber))  ");
        }
        if(customer.getLegalDocsType() != null){
            sqlBody.append(" AND LEGAL_DOCS_TYPE = (:parLegalDocsTypeId)  ");
        }
        if(StringUtils.isNotEmpty(customer.getLegalDocsNumber2())){
            sqlBody.append(" AND upper(LEGAL_DOCS_NUMBER2) LIKE upper((:parLegalDocsNumber2))  ");
        }
        if(customer.getLegalDocsType2() != null){
            sqlBody.append(" AND LEGAL_DOCS_TYPE2 = (:parLegalDocsType2Id)  ");
        }

        if(StringUtils.isNotEmpty(customer.getWorkflowStatus())){
            sqlBody.append(" AND WORKFLOWSTATUS LIKE (:parWorkFlowStatus)  ");
        }
        if(StringUtils.isNotEmpty(customer.getCode())){
            sqlBody.append(" AND CODE LIKE (:parCode)  ");
        }
        if(StringUtils.isNotEmpty(customer.getAccountNumber())){
            sqlBody.append(" AND ACCOUNT_NUMBER LIKE (:parAccountNumber)  ");
        }
        if(customer.getBranch() != null){
            sqlBody.append(" AND BRANCH = (:parBranchId)  ");
        }
        if(StringUtils.isNotEmpty(customer.getStatus())){
            sqlBody.append(" AND STATUS = (:parStatus)  ");
        }
        if(StringUtils.isNotEmpty(customer.getSwiftCode())){
            sqlBody.append(" AND SWIFT_CODE = (:parSwiftCode)  ");
        }
        if(StringUtils.isNotEmpty(customer.getReuterCode())){
            sqlBody.append(" AND REUTER_CODE = (:parReuterCode)  ");
        }

        sql.append(sqlBody);
        sql.append(" ) WHERE ROW_NUM >= :parFrom AND ROW_NUM < :parTo");

        sqlCount.append(sqlBody);
        sqlCount.append("  )");

        Query query = em.createNativeQuery(sql.toString(), Customer.class);
        Query queryCount = em.createNativeQuery(sqlCount.toString());

        //3. Set parameter for SQL statement
        if(customer.getCustomerType() != null){
            query.setParameter("parCustomerTypeId", customer.getCustomerType().getId());
            queryCount.setParameter("parCustomerTypeId", customer.getCustomerType().getId());
        }
        if(StringUtils.isNotEmpty(customer.getFullName())){
            query.setParameter("parFullName", customer.getFullName());
            queryCount.setParameter("parFullName", customer.getFullName());
        }
        if (StringUtils.isNotEmpty(customer.getLegalDocsNumber())) {
            query.setParameter("parLegalDocsNumber", customer.getLegalDocsNumber());
            queryCount.setParameter("parLegalDocsNumber", customer.getLegalDocsNumber());
        }
        if (customer.getLegalDocsType() != null) {
            query.setParameter("parLegalDocsTypeId", customer.getLegalDocsType().getId());
            queryCount.setParameter("parLegalDocsTypeId", customer.getLegalDocsType().getId());
        }
        if (StringUtils.isNotEmpty(customer.getLegalDocsNumber2())) {
            query.setParameter("parLegalDocsNumber2", customer.getLegalDocsNumber2());
            queryCount.setParameter("parLegalDocsNumber2", customer.getLegalDocsNumber2());
        }
        if (customer.getLegalDocsType2() != null) {
            query.setParameter("parLegalDocsType2Id", customer.getLegalDocsType2().getId());
            queryCount.setParameter("parLegalDocsType2Id", customer.getLegalDocsType2().getId());
        }

        if(StringUtils.isNotEmpty(customer.getWorkflowStatus())){
            query.setParameter("parWorkFlowStatus", customer.getWorkflowStatus());
            queryCount.setParameter("parWorkFlowStatus", customer.getWorkflowStatus());
        }
        if(StringUtils.isNotEmpty(customer.getCode())){
            query.setParameter("parCode", customer.getCode());
            queryCount.setParameter("parCode", customer.getCode());
        }
        if(StringUtils.isNotEmpty(customer.getAccountNumber())){
            query.setParameter("parAccountNumber", customer.getAccountNumber());
            queryCount.setParameter("parAccountNumber", customer.getAccountNumber());
        }
        if(customer.getBranch() != null){
            query.setParameter("parBranchId", customer.getBranch().getId());
            queryCount.setParameter("parBranchId", customer.getBranch().getId());
        }
        if(StringUtils.isNotEmpty(customer.getStatus())){
            query.setParameter("parStatus", customer.getStatus());
            queryCount.setParameter("parStatus", customer.getStatus());
        }
        if(StringUtils.isNotEmpty(customer.getSwiftCode())){
            query.setParameter("parSwiftCode", customer.getSwiftCode());
            queryCount.setParameter("parSwiftCode", customer.getSwiftCode());
        }
        if(StringUtils.isNotEmpty(customer.getReuterCode())){
            query.setParameter("parReuterCode", customer.getReuterCode());
            queryCount.setParameter("parReuterCode", customer.getReuterCode());
        }
        if( StringUtils.isEmpty( customer.getWorkflowStatus()) || Status.SAVE_DRAFT.equalsIgnoreCase(customer.getWorkflowStatus())) {
            query.setParameter("parCreatedById", customer.getCreatedBy().getId());
            queryCount.setParameter("parCreatedById", customer.getCreatedBy().getId());
        }
        query.setParameter("parFrom", parFrom);
        query.setParameter("parTo", parTo);

        //4. Execute SQL statement
        List<Customer> customers = query.getResultList();
        List<BigDecimal> lsInt = queryCount.getResultList();
        if (lsInt != null){
            totalRows = Long.parseLong(lsInt.get(0).toString());
            totalPages = (int) Math.ceil((double) totalRows / pageable.getPageSize());
        }

        PagedResource<Customer> result = new PagedResource<Customer>(customers,pageable.getPageNumber(),pageable.getPageSize(),totalPages, totalRows);

        return result;
    }
    
}
