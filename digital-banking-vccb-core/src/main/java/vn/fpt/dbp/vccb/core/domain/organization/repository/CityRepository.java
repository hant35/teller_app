package vn.fpt.dbp.vccb.core.domain.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.organization.City;
import vn.fpt.dbp.vccb.core.domain.organization.Country;

@Component
public interface CityRepository extends JpaRepository<City, Long>, QueryDslPredicateExecutor<City> {
	public List<City> findByCountry(Country country);

	public City findByCode(String code);

	public List<City> findByName(String name);
}
