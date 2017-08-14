package vn.fpt.dbp.vccb.core.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.product.ProductPromotion;

@Component
public interface ProductPromotionRepository
		extends JpaRepository<ProductPromotion, Long>, QueryDslPredicateExecutor<ProductPromotion> {

}
