package vn.fpt.dbp.vccb.core.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.base.DBPRepository;
import vn.fpt.dbp.vccb.core.domain.product.Product;

import java.util.List;

@Component
public interface ProductRepository extends JpaRepository<Product, Long>, QueryDslPredicateExecutor<Product>, DBPRepository<Product> {
	public Product findByCode(String code);
	public List<Product> findByName(String name);
	public Product findTop1ByCodeAndWorkflowStatusOrderByApprovedDateAsc(String code, String workflowStatus);
	public Product findTop1ByCodeAndWorkflowStatusOrderByApprovedDateDesc(String code, String workflowStatus);
	public List<Product> findByNameLikeIgnoreCase(String name);
}
