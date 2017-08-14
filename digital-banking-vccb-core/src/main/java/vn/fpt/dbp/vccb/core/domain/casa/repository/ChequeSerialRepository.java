package vn.fpt.dbp.vccb.core.domain.casa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.casa.ChequeSerial;
import vn.fpt.dbp.vccb.core.domain.casa.SavingBookSerial;

@Component
public interface ChequeSerialRepository
		extends JpaRepository<ChequeSerial, Long>, QueryDslPredicateExecutor<ChequeSerial> {
	public SavingBookSerial findByCode(String code);

}
