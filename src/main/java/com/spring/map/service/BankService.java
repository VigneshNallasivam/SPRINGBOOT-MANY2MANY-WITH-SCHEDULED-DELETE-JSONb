package com.spring.map.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.spring.map.model.Bank;
import com.spring.map.repository.BankRepository;

@Service
public class BankService 
{
//	private static EntityManagerFactory entityManagerFactory2 =
//            Persistence.createEntityManagerFactory("example-unit");
	@Autowired
	BankRepository bankRepository;

	public Bank create(Bank bank) 
	{
		return bankRepository.save(bank);
	}
	
	public Bank update(Long id,Bank bank) throws Exception 
	{
		Bank bank2 = bankRepository.findById(id).get();
		if(bankRepository.findById(id).isPresent())
		{
			bank2.setBankName(bank.getBankName());
			bank2.setBankIFSC(bank.getBankIFSC());
			bank2.setBankBranch(bank.getBankBranch());
			bankRepository.save(bank2);
			return bank2;
		}
		else
		{
			throw new Exception("ID NOT-FOUND..!!");
		}
		
	}

	public Page<Bank> read(Pageable pageable)
	{
		return bankRepository.findAllByPagination(pageable);
	}

	public void delete(Long id) throws Exception
	{
		if(bankRepository.findById(id).isPresent())
		{
			bankRepository.deleteById(id);
		}
		else
		{
			throw new Exception("ID NOT-FOUND..!!");
		}

	}

	@Scheduled(cron = "0 50 16  * * *")
	public void scheduledDelete() throws Exception
	{
		bankRepository.deleteByBankName();
    }


}
