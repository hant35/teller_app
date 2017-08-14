package vn.fpt.dbp.vccb.core.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;
import vn.fpt.dbp.vccb.core.domain.product.ProductAccountClass;

@Component
public interface ProductAccountClassRepository extends JpaRepository<ProductAccountClass, Long>, QueryDslPredicateExecutor<ProductAccountClass> {
    public ProductAccountClass findByCode(String code);
}
