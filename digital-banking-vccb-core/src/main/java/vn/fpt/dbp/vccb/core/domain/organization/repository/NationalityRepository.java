package vn.fpt.dbp.vccb.core.domain.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.organization.Nationality;

@Component
public interface NationalityRepository
		extends JpaRepository<Nationality, Long>, QueryDslPredicateExecutor<Nationality> {
	public Nationality findByCode(String code);

	public List<Nationality> findByName(String name);
}
