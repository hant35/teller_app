package vn.fpt.dbp.vccb.core.domain.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.system.DataTableDetail;

@Component
public interface DataTableDetailRepository extends JpaRepository<DataTableDetail, Long>, QueryDslPredicateExecutor<DataTableDetail>{

}
