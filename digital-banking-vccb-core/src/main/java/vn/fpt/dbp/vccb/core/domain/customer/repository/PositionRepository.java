package vn.fpt.dbp.vccb.core.domain.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.customer.Position;

@Component
public interface PositionRepository extends JpaRepository<Position, Long>, QueryDslPredicateExecutor<Position> {
	public Position findByCode(String code);
}
