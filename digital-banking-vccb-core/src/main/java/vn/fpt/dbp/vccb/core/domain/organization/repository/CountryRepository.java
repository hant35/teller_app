package vn.fpt.dbp.vccb.core.domain.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.organization.Country;

@Component
public interface CountryRepository extends JpaRepository<Country, Long>, QueryDslPredicateExecutor<Country> {
	public Country findByCode(String code);

	public List<Country> findByName(String name);
}
