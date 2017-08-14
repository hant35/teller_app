package vn.fpt.dbp.vccb.core.domain.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.customer.FinancialInstitutionsType;

@Component
@Deprecated
public interface FinancialInstitutionsTypeRepository
		extends JpaRepository<FinancialInstitutionsType, Long>, QueryDslPredicateExecutor<FinancialInstitutionsType> {
	public FinancialInstitutionsType findByCode(String code);

}
