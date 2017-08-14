package vn.fpt.dbp.vccb.core.domain.product.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;

import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.product.Product;
import vn.fpt.dbp.vccb.core.domain.product.ProductCustomer;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.rest.PagedResource;

public class ProductRepositoryImpl implements DBPRepository<Product>{
    @PersistenceContext
    private EntityManager em;

    public PagedResource<Product> searchLastedVersion(Product product, Pageable pageable){
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
        sqlBody.append(" (SELECT WF.*, PR.CODE, PR.DESCRIPTION, PR.NAME, PR.AGE_FROM, PR.AGE_TO, PR.EFFECTED_DATE, " );
        sqlBody.append(" PR.EXPIRED_DATE, PR.GENDER, PR.IS_AUTO_BLOCK, PR.TRANSFERABLE, PR.AUTO_BLOCK_TYPE, " );
        sqlBody.append(" PR.DEPOSIT_TYPE, MAX(CREATED_DATE) OVER(PARTITION BY ORIGINAL_ID, CODE) MAX_DATE" );
          //Join
        sqlBody.append(" FROM PRODUCT PR JOIN WORKFLOW_MODEL WF ON PR.ID = WF.ID  ");
        //Loại bỏ rejected row cho trường hợp search all.
        if(StringUtils.isEmpty( product.getWorkflowStatus())){
            sqlBody.append(" WHERE WORKFLOWSTATUS NOT LIKE 'REJECTED'");
            sqlBody.append(" AND CREATED_BY = DECODE (WORKFLOWSTATUS, 'DRAFT', :parCreatedById, CREATED_BY) ");
        }else{
            sqlBody.append(" WHERE WORKFLOWSTATUS LIKE (:parWorkFlowStatus)  ");

            //Thêm nhánh trường hợp lọc save_draft row cho login user.
            //Trường hợp login user là null sẽ trả về kết quả rỗng.
            if(Status.SAVE_DRAFT.equalsIgnoreCase(product.getWorkflowStatus())){
                sqlBody.append(" AND CREATED_BY = :parCreatedById  ");
            }
        }
        sqlBody.append(" ORDER BY CREATED_DATE DESC ) A ");
          //Condition
        sqlBody.append(" WHERE CREATED_DATE = MAX_DATE  ");
//        sqlBody.append(" OR ORIGINAL_ID IS NULL ");
        if(StringUtils.isNotEmpty(product.getCode())){
            sqlBody.append(" AND upper(CODE) LIKE upper((:parCode))  ");
        }
        if(StringUtils.isNotEmpty(product.getName())){
            sqlBody.append(" AND upper(NAME) LIKE upper((:parName))  ");
        }
        if (product.getDepositType() != null) {
            sqlBody.append(" AND DEPOSIT_TYPE = :parDepositTypeId  ");
        }
        if(product.getAgeFrom() != null && product.getAgeTo() != null){
            sqlBody.append(" AND AGE_FROM >= :parAgeFrom  ");
            sqlBody.append(" AND AGE_TO <= :parAgeTo  ");
        }
        if (product.getProductCustomers() != null) {
            for (ProductCustomer productCustomer : product.getProductCustomers()
                    ) {
                if (productCustomer.getCustomerType() != null) {
                    sqlBody.append(" AND ID IN (SELECT DISTINCT (PRODUCT) FROM PRODUCT_CUSTOMER PC INNER JOIN CUSTOMER_TYPE CT ON PC.CUSTOMER_TYPE = :parCustomerTypeId");
                }
                if (productCustomer.getCustomerSize() != null) {
                    sqlBody.append(" AND ID IN (SELECT DISTINCT (PRODUCT) FROM PRODUCT_CUSTOMER PC INNER JOIN CUSTOMER_SIZE CS ON PC.CUSTOMER_SIZE = :parCustomerSizeId");
                }
                break;
            }
        }
        if (product.getEffectedDate() != null) {
            sqlBody.append(" AND EFFECTED_DATE >= :parEffectedDate  ");
        }
        if (product.getExpiredDate() != null) {
            sqlBody.append(" AND EXPIRED_DATE >= :parExpiredDate  ");
        }
        if (product.getProcessInstanceId() != null) {
            sqlBody.append(" AND PROCESS_INSTANCE_ID = :parProcessInstanceId  ");
        }
        if (product.getTaskId() != null) {
            sqlBody.append(" AND TASK_ID  = :parTaskId  ");
        }
        if(product.getTransferable() != null) {
            sqlBody.append(" AND TRANSFERABLE  = :parTransferable  ");
        }
        if(product.getGender() != null) {
            sqlBody.append(" AND upper(GENDER) LIKE upper((:parGender))  ");
        }

        sql.append(sqlBody);
        sql.append(" ) WHERE ROW_NUM >= :parFrom AND ROW_NUM < :parTo");

        sqlCount.append(sqlBody);
        sqlCount.append("  )");

        Query query = em.createNativeQuery(sql.toString(), Product.class);
        Query queryCount = em.createNativeQuery(sqlCount.toString());

        //3. Set parameter for SQL statement
        if(StringUtils.isNotEmpty(product.getCode())){
            query.setParameter("parCode", product.getCode());
            queryCount.setParameter("parCode", product.getCode());
        }
        if(StringUtils.isNotEmpty(product.getName())){
            query.setParameter("parName", product.getName());
            queryCount.setParameter("parName", product.getName());
        }
        if (product.getDepositType() != null) {
            query.setParameter("parDepositTypeId", product.getDepositType().getId());
            queryCount.setParameter("parDepositTypeId", product.getDepositType().getId());
        }
        if(product.getAgeFrom() != null && product.getAgeTo() != null){
            query.setParameter("parAgeFrom", product.getAgeFrom());
            query.setParameter("parAgeTo", product.getAgeFrom());
            queryCount.setParameter("parAgeFrom", product.getAgeFrom());
            queryCount.setParameter("parAgeTo", product.getAgeFrom());
        }
        if (product.getProductCustomers() != null) {
            for (ProductCustomer productCustomer : product.getProductCustomers()
                    ) {
                if (productCustomer.getCustomerType() != null) {
                    query.setParameter("parCustomerTypeId", productCustomer.getCustomerType().getId());
                    queryCount.setParameter("parCustomerTypeId", productCustomer.getCustomerType().getId());
                }
                if (productCustomer.getCustomerSize() != null) {
                    query.setParameter("parCustomerSizeId", productCustomer.getCustomerSize().getId());
                    queryCount.setParameter("parCustomerSizeId", productCustomer.getCustomerSize().getId());
                }
                break;
            }
        }
        if (product.getEffectedDate() != null) {
            query.setParameter("parEffectedDate", product.getEffectedDate());
            queryCount.setParameter("parEffectedDate", product.getEffectedDate());
        }
        if (product.getExpiredDate() != null) {
            query.setParameter("parExpiredDate", product.getExpiredDate());
            queryCount.setParameter("parExpiredDate", product.getExpiredDate());
        }
        if (product.getWorkflowStatus() != null) {
            query.setParameter("parWorkFlowStatus", product.getWorkflowStatus());
            queryCount.setParameter("parWorkFlowStatus", product.getWorkflowStatus());
        }
        if (product.getProcessInstanceId() != null) {
            query.setParameter("parProcessInstanceId", product.getProcessInstanceId());
            queryCount.setParameter("parProcessInstanceId", product.getProcessInstanceId());
        }
        if (product.getTaskId() != null) {
            query.setParameter("parTaskId", product.getTaskId());
            queryCount.setParameter("parTaskId", product.getTaskId());
        }
        if(product.getTransferable() != null) {
            query.setParameter("parTransferable", product.getTransferable());
            queryCount.setParameter("parTransferable", product.getTransferable());
        }
        if(product.getGender() != null) {
            query.setParameter("parGender", product.getGender());
            queryCount.setParameter("parGender", product.getGender());
        }
        if( StringUtils.isEmpty( product.getWorkflowStatus()) || Status.SAVE_DRAFT.equalsIgnoreCase(product.getWorkflowStatus())) {
            query.setParameter("parCreatedById", product.getCreatedBy().getId());
            queryCount.setParameter("parCreatedById", product.getCreatedBy().getId());
        }
        query.setParameter("parFrom", parFrom);
        query.setParameter("parTo", parTo);

        //4. Execute SQL statement
        List<Product> products = query.getResultList();
        List<BigDecimal> lsInt = queryCount.getResultList();
        if (lsInt != null){
            totalRows = Long.parseLong(lsInt.get(0).toString());
            totalPages = (int) Math.ceil((double) totalRows / pageable.getPageSize());
        }

        PagedResource<Product> result = new PagedResource<Product>(products,pageable.getPageNumber(),pageable.getPageSize(),totalPages, totalRows);

        return result;

    }
}
