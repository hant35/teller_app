package vn.fpt.dbp.vccb.core.domain.interest.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.currency.Currency;
import vn.fpt.dbp.vccb.core.domain.interest.Interest;
import vn.fpt.dbp.vccb.core.domain.interest.TimeRate;
import vn.fpt.dbp.vccb.core.domain.organization.Area;
import vn.fpt.dbp.vccb.core.domain.organization.Branch;
import vn.fpt.dbp.vccb.core.domain.product.Product;

@Component
public interface InterestRepository
		extends JpaRepository<Interest, Long>, QueryDslPredicateExecutor<Interest>, DBPRepository<Interest> {

	public Interest findTop1ByProductAndAreaAndBranchAndCurrencyAndTimeRateAndEffectDateAndWorkflowStatusOrderByApprovedDateAsc(
			Product product, Area area, Branch branch, Currency currency, TimeRate timeRate, Date effectDate,
			String workflowStatus);

	public Interest findTop1ByProductAndAreaAndBranchAndCurrencyAndTimeRateAndEffectDateAndWorkflowStatusOrderByApprovedDateDesc(
			Product product, Area area, Branch branch, Currency currency, TimeRate timeRate, Date effectDate,
			String workflowStatus);

	public Interest findTop1ByProductAndBranchAndCurrencyAndTimeRateAndWorkflowStatusOrderByApprovedDateAsc(
			Product product, Branch branch, Currency currency, TimeRate timeRate, String workflowStatus);

	public Interest findTop1ByProductAndBranchAndCurrencyAndTimeRateAndWorkflowStatusOrderByApprovedDateDesc(
			Product product, Branch branch, Currency currency, TimeRate timeRate, String workflowStatus);
}