package vn.fpt.dbp.vccb.core.domain.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.organization.District;

@Component
public interface DistrictRepository extends JpaRepository<District, Long>, QueryDslPredicateExecutor<District> {
	public District findByCode(String code);

	public List<District> findByName(String name);
}
