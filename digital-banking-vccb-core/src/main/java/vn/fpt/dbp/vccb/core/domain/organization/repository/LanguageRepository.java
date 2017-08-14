package vn.fpt.dbp.vccb.core.domain.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.organization.Language;

@Component
public interface LanguageRepository extends JpaRepository<Language, Long>, QueryDslPredicateExecutor<Language> {
	public Language findByCode(String code);

	public Language findByCodeEqualsIgnoreCase(String code);

	public List<Language> findByName(String name);
}
