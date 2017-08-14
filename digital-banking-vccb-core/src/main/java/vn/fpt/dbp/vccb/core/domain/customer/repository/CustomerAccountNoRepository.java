package vn.fpt.dbp.vccb.core.domain.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.customer.Customer;
import vn.fpt.dbp.vccb.core.domain.customer.CustomerAccountNo;

@Component
public interface CustomerAccountNoRepository
		extends JpaRepository<CustomerAccountNo, Long>, QueryDslPredicateExecutor<CustomerAccountNo> {

	Long countByCustomer(Customer customer);

	Long countByCustomerAndIsActive(Customer customer, Boolean isActive);

	Long countByAccountNo(String accountNo);

	Long countByAccountNoAndIsActive(String accountNo, Boolean isActive);
}
