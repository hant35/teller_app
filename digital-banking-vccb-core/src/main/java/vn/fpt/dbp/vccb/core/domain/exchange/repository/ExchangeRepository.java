package vn.fpt.dbp.vccb.core.domain.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.exchange.Exchange;
import vn.fpt.dbp.vccb.core.domain.organization.Area;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;

@Component
public interface ExchangeRepository extends JpaRepository<Exchange, Long>, QueryDslPredicateExecutor<Exchange>, DBPRepository<Exchange>{
    public Exchange findTop1ByAreaAndBranchAndCurrencyFromAndCurrencyToAndWorkflowStatusOrderByApprovedDateDesc(Area area, Branch branch, Currency currencyFrom, Currency currencyTo, String workFlowStatus);
}
