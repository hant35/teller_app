package vn.fpt.dbp.vccb.core.domain.limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.limit.LimitDetail;

@Component
public interface LimitDetailRepository extends JpaRepository<LimitDetail, Long>, QueryDslPredicateExecutor<LimitDetail> {

}
