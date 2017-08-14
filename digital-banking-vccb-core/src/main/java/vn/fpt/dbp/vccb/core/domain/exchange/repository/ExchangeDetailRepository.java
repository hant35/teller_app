package vn.fpt.dbp.vccb.core.domain.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.base.CustomRepository;
import vn.fpt.dbp.vccb.core.domain.exchange.Exchange;
import vn.fpt.dbp.vccb.core.domain.exchange.ExchangeDetail;

@Component
public interface ExchangeDetailRepository
		extends JpaRepository<ExchangeDetail, Long>, QueryDslPredicateExecutor<ExchangeDetail>, CustomRepository<ExchangeDetail, Exchange> {

}
