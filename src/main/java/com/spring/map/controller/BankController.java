package com.spring.map.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.map.model.Bank;
import com.spring.map.response.Message;
import com.spring.map.response.Response;
import com.spring.map.service.BankService;

@RestController
@RequestMapping("/bank")
public class BankController 
{
	Logger logger = LoggerFactory.getLogger(BankController.class);
	@Autowired
	BankService bankService;
	
	@GetMapping("/bank")
	public Bank getJSON_POJO()
	{
		Bank bank = new Bank();
		return bank;
	}
	
	@PostMapping("/bankPost")
	public ResponseEntity<Object> create(@RequestBody Bank bank)
	{
		try
		{
			Bank bank1 = bankService.create(bank);
			Message message = new Message("Data Added..!!",bank1);
			return Response.generateResponse("Data Addition = Success!!",true,HttpStatus.CREATED, message);
			
		}
		catch (Exception ex) 
		{
			logger.error("Error while Create all category data: {}", ex.getMessage());
			return Response.generateResponse("Check Server Error..!!",false,HttpStatus.BAD_REQUEST,null);
		}

	}
	
	@PutMapping("/deptPut")
	public ResponseEntity<Object> update(Long id,@RequestBody Bank department)
	{
		try
		{
			Bank bank1 = bankService.update(id,department);
			Message msg = new Message("DATA UPDATED..!!",bank1);
			return Response.generateResponse("Data Updation = Success",true,HttpStatus.CONTINUE, msg);
		}
		catch(Exception ex)
		{
			logger.error("Error while Update all category data: {}", ex.getMessage());
			return Response.generateResponse("Check Server Error..!!",false,HttpStatus.BAD_REQUEST,null);
		}
	}
	
	@GetMapping("/deptGet")
	public ResponseEntity<Object> read(@PageableDefault(size = 10, page = 0, sort = "deptName", direction = Direction.ASC) Pageable pageable)
	{
		try
		{
		Page<Bank> departmentList = bankService.read(pageable);
		Message message = new Message("Data Readed..!!",departmentList);
		return Response.generateResponse("Data Reading = Success",true,HttpStatus.FOUND, message);
		}
		catch (Exception ex)
		{
			logger.error("Error while get all category data: {}", ex.getMessage());
			return Response.generateResponse("Check Server Error..!!",false,HttpStatus.BAD_GATEWAY,null);
        }
	}
	
	@DeleteMapping("/deptDelete")
	public ResponseEntity<Object> delete(Long id)
	{
		try
		{
		bankService.delete(id);
		Message message = new Message("Data Deleted..!!");
		return Response.generateResponse("Data Deletion = Success",true,HttpStatus.GONE, message);
		}
		catch(Exception ex)
		{
			logger.error("Error while Delete category data: {}", ex.getMessage());
			return Response.generateResponse("Check Client/Server Error..!!",false,HttpStatus.BAD_GATEWAY,null);
		}
	}
	
	@GetMapping("/scheduledDelete")
	public ResponseEntity<Object> scheduledDelete()
	{
		try
		{
		bankService.scheduledDelete();
		Message message = new Message("Data Deleted on Scheduling..!!");
		logger.info("Id Deleted on :"+LocalDateTime.now());
		return Response.generateResponse("Data Deletion on Schedule = Success",true,HttpStatus.GONE, message);
		}
		catch(Exception ex)
		{
			logger.error("Error while Delete category data: {}", ex.getMessage());
			return Response.generateResponse("Check Client/Server Error..!!",false,HttpStatus.BAD_GATEWAY,null);
		}
	}


}
