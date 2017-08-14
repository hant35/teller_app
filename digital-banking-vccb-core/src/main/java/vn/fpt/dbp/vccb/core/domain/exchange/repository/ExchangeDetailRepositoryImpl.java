package vn.fpt.dbp.vccb.core.domain.exchange.repository;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import vn.fpt.dbp.vccb.core.domain.base.CustomRepository;
import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.exchange.Exchange;
import vn.fpt.dbp.vccb.core.domain.exchange.ExchangeDetail;
import vn.fpt.dbp.vccb.core.util.Status;
import vn.fpt.util.rest.PagedResource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

public class ExchangeDetailRepositoryImpl implements CustomRepository <ExchangeDetail, Exchange> {
    @PersistenceContext
    private EntityManager em;

    public PagedResource<ExchangeDetail> searchLastedVersionCust(Exchange exchange, Pageable pageable) {
        StringBuilder sqlQuery = new StringBuilder();
        StringBuilder sqlQueryCount = new StringBuilder();
        StringBuilder sqlBody = new StringBuilder();
        StringBuilder sqlFooter = new StringBuilder();
//        String groupKey = "AREA, BRANCH, CURRENCY_FROM, CURRENCY_TO, EXCHANGE_UPLOAD_FILE";
        Integer parRowFrom, parRowTo, totalPages;
        Long totalRows;

        //1. Create value for parameters from input data
        parRowFrom = pageable.getPageNumber() * pageable.getPageSize() + 1;
        parRowTo = (pageable.getPageNumber() + 1) * pageable.getPageSize() + 1;
        totalPages = 0;
        totalRows = new Long(0);

        //2. Build SQL statement
            //Header
        sqlQuery.append(" SELECT ID, EXCHANGE, EXCHANGE_TYPE, MID_RATE, BUY_BAND, SELL_BAND, BUY_PRICE, SELL_PRICE, EFFECTED_DATE, CHANGED_NUMBER FROM ");

        sqlQueryCount.append(" SELECT COUNT(ID) FROM ");

        sqlBody.append(" (SELECT A.*, ROWNUM as ROW_NUM FROM ");
        sqlBody.append(" (SELECT S.*, ");
        sqlBody.append("  MAX(CREATED_DATE) OVER(PARTITION BY ORIGINAL_ID, AREA, BRANCH, CURRENCY_FROM, CURRENCY_TO) MAX_DATE FROM ");
        //Join
        sqlBody.append(" (SELECT S1.* FROM(" );
        sqlBody.append(" (SELECT WF.ORIGINAL_ID, WF.CREATED_BY, WF.CREATED_DATE, WF.WORKFLOWSTATUS, EX.AREA, EX.BRANCH ,EX.CURRENCY_FROM, EX.CURRENCY_TO,  EX_D.* " );
        sqlBody.append(" FROM WORKFLOW_MODEL WF " );
        sqlBody.append(" JOIN EXCHANGE EX " );
        sqlBody.append(" ON WF.ID = EX.ID " );
        sqlBody.append(" JOIN EXCHANGE_DETAIL EX_D " );
        sqlBody.append(" ON EX.EXCHANGE_UPLOAD_FILE = EX_D.ID) S1 " );
        sqlBody.append(" JOIN " );
        sqlBody.append(" (SELECT  WF.ORIGINAL_ID, WF.CREATED_BY, WF.CREATED_DATE, WF.WORKFLOWSTATUS, EX.AREA, EX.BRANCH ,EX.CURRENCY_FROM, EX.CURRENCY_TO,  EX_D.* " );
        sqlBody.append(" FROM WORKFLOW_MODEL WF " );
        sqlBody.append(" JOIN EXCHANGE EX " );
        sqlBody.append(" ON WF.ID = EX.ID " );
        sqlBody.append(" JOIN EXCHANGE_DETAIL EX_D " );
        sqlBody.append(" ON EX.EXCHANGE_UPLOAD_FILE = EX_D.ID " );
        sqlBody.append(" WHERE EX.EXCHANGE_UPLOAD_FILE  IS NOT null) S2 " );
        sqlBody.append(" ON S1.ID = S2.ID ) ) S " );
        if(StringUtils.isEmpty( exchange.getWorkflowStatus())){
            sqlBody.append(" WHERE WORKFLOWSTATUS NOT LIKE 'REJECTED'");
            sqlBody.append(" AND CREATED_BY = DECODE (WORKFLOWSTATUS, 'DRAFT', :parCreatedById, CREATED_BY) ");
        }else{
            sqlBody.append(" WHERE WORKFLOWSTATUS LIKE (:parWorkFlowStatus)  ");

            //Thêm nhánh trường hợp lọc save_draft row cho login user.
            //Trường hợp login user là null sẽ trả về kết quả rỗng.
            if(Status.SAVE_DRAFT.equalsIgnoreCase(exchange.getWorkflowStatus())){
                sqlBody.append(" AND CREATED_BY = :parCreatedById  ");
            }
        }
        sqlBody.append("  ORDER BY CREATED_DATE DESC) A ");
//       ....CONDITION
        sqlBody.append("  WHERE CREATED_DATE = MAX_DATE  ");
        if (exchange.getArea() != null) {
            sqlBody.append("  AND EX.AREA = :parAreaId  ");
        }
        if (exchange.getBranch() != null) {
            sqlBody.append("  AND EX.BRANCH = :parBranchId  ");
        }
        if (exchange.getCurrencyFrom() != null) {
            sqlBody.append("  AND EX.CURRENCY_FROM = :parCurrencyFrom  ");
        }
        if (exchange.getCurrencyFrom() != null) {
            sqlBody.append("  AND EX.CURRENCY_TO = :parCurrencyTo  ");
        }
        if (exchange.getFromDate() != null) {
            sqlBody.append("  AND ID IN (SELECT DISTINCT(EXCHANGE) FROM EXCHANGE_DETAIL WHERE EFFECTED_DATE >= :parFromDate  ");
        }

        if (exchange.getToDate() != null) {
            sqlBody.append("  AND ID IN (SELECT DISTINCT(EXCHANGE) FROM EXCHANGE_DETAIL WHERE EFFECTED_DATE <= :parToDate  ");
        }

        //--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
        if (exchange.getProcessInstanceId() != null) {
            sqlBody.append("  AND PROCESS_INSTANCE_ID = parProcessInstanceId");
        }
        if (exchange.getTaskId() != null) {
            sqlBody.append("  AND TASK_ID = parTaskId");
        }

        if (exchange.getExchangeDetails() != null && exchange.getExchangeDetails().size() > 0){
            for (ExchangeDetail exchangeDetail : exchange.getExchangeDetails()) {
                if (exchangeDetail.getExchangeType() != null) {
                    sqlBody.append("  AND ID IN (SELECT DISTINCT(EXCHANGE) FROM EXCHANGE_DETAIL WHERE EXCHANGE_TYPE like (:parExchangeType) )  ");
                }
                break;
            }
        }

        sqlQuery.append(sqlBody);
        sqlQuery.append(" ) WHERE ROW_NUM >= :parRowFrom AND ROW_NUM < :parRowTo");

        sqlQueryCount.append(sqlBody);
        sqlQueryCount.append(" )");

        Query query = em.createNativeQuery(sqlQuery.toString(), ExchangeDetail.class);
        Query queryCount = em.createNativeQuery(sqlQueryCount.toString());

        //3. Set parameter for SQL statement
        if (StringUtils.isNotEmpty(exchange.getWorkflowStatus())) {
            query.setParameter("parWorkFlowStatus", exchange.getWorkflowStatus());
            queryCount.setParameter("parWorkFlowStatus", exchange.getWorkflowStatus());
        }
        if (exchange.getArea() != null) {
            query.setParameter("parAreaId", exchange.getArea().getId());
            queryCount.setParameter("parAreaId", exchange.getArea().getId());
        }
        if (exchange.getBranch() != null) {
            query.setParameter("parBranchId", exchange.getBranch().getId());
            queryCount.setParameter("parBranchId", exchange.getBranch().getId());
        }
        if (exchange.getCurrencyFrom() != null) {
            query.setParameter("parCurrencyFromId", exchange.getCurrencyFrom().getId());
            queryCount.setParameter("parCurrencyFromId", exchange.getCurrencyFrom().getId());
        }
        if (exchange.getCurrencyTo() != null) {
            query.setParameter("parCurrencyToId", exchange.getCurrencyTo().getId());
            queryCount.setParameter("parCurrencyToId", exchange.getCurrencyTo().getId());
        }
        if (exchange.getFromDate() != null) {
            query.setParameter("parFromDate", exchange.getFromDate() );
            queryCount.setParameter("parFromDate", exchange.getFromDate());
        }
        if (exchange.getToDate() != null) {
            query.setParameter("parToDate", exchange.getToDate() );
            queryCount.setParameter("parToDate", exchange.getToDate());
        }

        //--- bo sung them theo yeu cau cua groovy team ngay 27/4/2017
        if (exchange.getProcessInstanceId() != null) {
            query.setParameter("parProcessInstanceId", exchange.getProcessInstanceId());
            queryCount.setParameter("parProcessInstanceId", exchange.getProcessInstanceId());
        }
        if (exchange.getTaskId() != null) {
            query.setParameter("parTaskId", exchange.getTaskId());
            queryCount.setParameter("parTaskId", exchange.getTaskId());
        }

        if (exchange.getExchangeDetails() != null && exchange.getExchangeDetails().size() > 0){
            for (ExchangeDetail exchangeDetail : exchange.getExchangeDetails()) {
                if (exchangeDetail.getExchangeType() != null) {
                    query.setParameter("parExchangeType", exchangeDetail.getExchangeType());
                    queryCount.setParameter("parExchangeType", exchangeDetail.getExchangeType());
                }
                break;
            }
        }
        if(StringUtils.isEmpty( exchange.getWorkflowStatus()) || Status.SAVE_DRAFT.equalsIgnoreCase(exchange.getWorkflowStatus())){
            query.setParameter("parCreatedById", exchange.getCreatedBy().getId());
            queryCount.setParameter("parCreatedById", exchange.getCreatedBy().getId());
        }

        query.setParameter("parRowFrom", parRowFrom);
        query.setParameter("parRowTo", parRowTo);

        //4. Execute SQL statement
        List<ExchangeDetail> exchangeDetails = query.getResultList();
        List<BigDecimal> lsInt = queryCount.getResultList();
        if (lsInt != null){
            totalRows = Long.parseLong(lsInt.get(0).toString());
            totalPages = (int) Math.ceil((double) totalRows / pageable.getPageSize());
        }

        PagedResource<ExchangeDetail> result = new PagedResource<ExchangeDetail>(exchangeDetails, pageable.getPageNumber(),pageable.getPageSize(),totalPages, totalRows);
        return result;
    }
}
