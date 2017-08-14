package vn.fpt.dbp.vccb.core.domain.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.exchange.ExchangeType;

@Component
public interface ExchangeTypeRepository
		extends JpaRepository<ExchangeType, Long>, QueryDslPredicateExecutor<ExchangeType> {

		public ExchangeType findByCode(String code);

}
