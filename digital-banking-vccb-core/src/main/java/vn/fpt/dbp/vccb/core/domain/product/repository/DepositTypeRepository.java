package vn.fpt.dbp.vccb.core.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.product.DepositType;

@Component
public interface DepositTypeRepository
		extends JpaRepository<DepositType, Long>, QueryDslPredicateExecutor<DepositType> {
	public DepositType findByCode(DepositType depositType);

}
