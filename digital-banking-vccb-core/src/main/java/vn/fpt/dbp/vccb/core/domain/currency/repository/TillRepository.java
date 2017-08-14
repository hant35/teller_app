package vn.fpt.dbp.vccb.core.domain.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.currency.Till;

@Component
public interface TillRepository extends JpaRepository<Till, Long>, QueryDslPredicateExecutor<Till>{
	public Till findByCode(String code);

}
