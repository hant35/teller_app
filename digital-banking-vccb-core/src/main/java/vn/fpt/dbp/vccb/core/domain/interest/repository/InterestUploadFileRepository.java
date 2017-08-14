package vn.fpt.dbp.vccb.core.domain.interest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.interest.InterestUploadFile;

@Component
public interface InterestUploadFileRepository
		extends JpaRepository<InterestUploadFile, Long>, QueryDslPredicateExecutor<InterestUploadFile> {

}