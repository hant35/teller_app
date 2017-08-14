package vn.fpt.dbp.vccb.core.domain.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.customer.LegalDocsType;

@Component
public interface LegalDocsTypeRepository
		extends JpaRepository<LegalDocsType, Long>, QueryDslPredicateExecutor<LegalDocsType> {
	public LegalDocsType findByCode(String code);
}
