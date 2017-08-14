package vn.fpt.dbp.vccb.core.domain.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.customer.Customer;
import vn.fpt.dbp.vccb.core.domain.customer.CustomerPaymentInfo;

@Component
public interface CustomerPaymentInfoRepository
		extends JpaRepository<CustomerPaymentInfo, Long>, QueryDslPredicateExecutor<CustomerPaymentInfo> {
	public List<CustomerPaymentInfo> findByCustomer(Customer customer);
}
