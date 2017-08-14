package vn.fpt.dbp.vccb.core.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.user.UserCustomerGroup;

@Component
public interface UserCustomerGroupRepository extends JpaRepository<UserCustomerGroup, Long>, QueryDslPredicateExecutor<UserCustomerGroup>{

}
