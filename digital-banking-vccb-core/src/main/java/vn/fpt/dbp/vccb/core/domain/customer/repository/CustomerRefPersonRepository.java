package vn.fpt.dbp.vccb.core.domain.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.customer.Customer;
import vn.fpt.dbp.vccb.core.domain.customer.CustomerRefPerson;

@Component
public interface CustomerRefPersonRepository
		extends JpaRepository<CustomerRefPerson, Long>, QueryDslPredicateExecutor<CustomerRefPerson> {
	public List<CustomerRefPerson> findByCustomer(Customer customer);
}
