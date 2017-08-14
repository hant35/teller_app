package vn.fpt.dbp.vccb.core.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.product.Promotion;

@Component
public interface PromotionRepository extends JpaRepository<Promotion, Long>, QueryDslPredicateExecutor<Promotion> {
	public Promotion findByCode(String code);

}
