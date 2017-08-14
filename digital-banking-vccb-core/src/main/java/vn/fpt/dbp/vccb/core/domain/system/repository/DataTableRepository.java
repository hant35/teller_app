package vn.fpt.dbp.vccb.core.domain.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.system.DataTable;

@Component
public interface DataTableRepository extends JpaRepository<DataTable, Long>, QueryDslPredicateExecutor<DataTable>{
	public DataTable findByScreenCodeAndTableCode(String screenCode,String tableCode);

}
