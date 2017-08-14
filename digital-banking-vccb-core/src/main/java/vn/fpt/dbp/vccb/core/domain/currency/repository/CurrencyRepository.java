package vn.fpt.dbp.vccb.core.domain.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.currency.Currency;

@Component
public interface CurrencyRepository extends JpaRepository<Currency, Long>, QueryDslPredicateExecutor<Currency>{
	public Currency findByCode(String code);
	public Currency findById(long id);

}
