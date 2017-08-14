package vn.fpt.dbp.vccb.core.domain.interest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.interest.TimeRate;

@Component
public interface TimeRateRepository extends JpaRepository<TimeRate, Long>, QueryDslPredicateExecutor<TimeRate> {

	public TimeRate findByCode(String code);
}