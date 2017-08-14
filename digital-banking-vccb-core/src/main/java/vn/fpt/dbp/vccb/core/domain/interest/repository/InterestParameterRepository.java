package vn.fpt.dbp.vccb.core.domain.interest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.interest.InterestParameter;

@Component
public interface InterestParameterRepository
		extends JpaRepository<InterestParameter, Long>, QueryDslPredicateExecutor<InterestParameter> {
	public InterestParameter findByCode(String code);

}