package vn.fpt.dbp.vccb.core.domain.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.currency.Vault;

@Component
public interface VaultRepository extends JpaRepository<Vault, Long>, QueryDslPredicateExecutor<Vault>{
	public Vault findByCode(String code);

}
