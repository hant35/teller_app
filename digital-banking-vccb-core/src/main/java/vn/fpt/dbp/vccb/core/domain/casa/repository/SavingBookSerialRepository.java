package vn.fpt.dbp.vccb.core.domain.casa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Component;

import vn.fpt.dbp.vccb.core.domain.casa.SavingBookSerial;

@Component
public interface SavingBookSerialRepository
		extends JpaRepository<SavingBookSerial, Long>, QueryDslPredicateExecutor<SavingBookSerial> {
	public SavingBookSerial findByCode(String code);

}
