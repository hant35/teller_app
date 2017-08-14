package vn.fpt.dbp.vccb.core.domain.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.customer.Customer;

@Component
public interface CustomerRepository
		extends JpaRepository<Customer, Long>, QueryDslPredicateExecutor<Customer>, DBPRepository<Customer> {
	public Customer findTop1ByCodeAndWorkflowStatusOrderByApprovedDateDesc(String code, String workflowstatus);
}
