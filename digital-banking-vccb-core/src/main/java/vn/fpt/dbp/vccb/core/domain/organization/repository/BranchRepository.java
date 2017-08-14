package vn.fpt.dbp.vccb.core.domain.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.organization.Branch;

@Component
public interface BranchRepository extends JpaRepository<Branch, Long>, QueryDslPredicateExecutor<Branch>{
	public Branch findByCode(String code);

}
