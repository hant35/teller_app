package vn.fpt.dbp.vccb.core.domain.exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.exchange.ExchangeUploadFile;

import java.util.Date;

@Component
public interface ExchangeUploadFileRepository
		extends JpaRepository<ExchangeUploadFile, Long>, QueryDslPredicateExecutor<ExchangeUploadFile> {

		public ExchangeUploadFile findTop1ByFileNameAndUploadDateAndWorkflowStatusOrderByApprovedDateDesc(String fileName, Date uploadDate, String workflowstatus);
}
