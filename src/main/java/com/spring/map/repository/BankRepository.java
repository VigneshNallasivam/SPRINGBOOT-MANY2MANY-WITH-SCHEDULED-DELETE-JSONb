package com.spring.map.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.map.model.Bank;

@Repository
@Transactional
public interface BankRepository extends JpaRepository<Bank,Long>
{
	
	@Query("SELECT b FROM Bank b")
	Page<Bank> findAllByPagination(Pageable pageable);

	@Modifying
	@Query("DELETE FROM Bank b WHERE b.bankName ='Axis'")
	void deleteByBankName();
}
