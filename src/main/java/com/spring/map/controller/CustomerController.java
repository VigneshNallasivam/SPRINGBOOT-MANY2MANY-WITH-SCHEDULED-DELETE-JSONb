package com.spring.map.controller;

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

import com.spring.map.model.Customer;
import com.spring.map.response.Message;
import com.spring.map.response.Response;
import com.spring.map.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	CustomerService customerService;

	@GetMapping("/bank")
	public Customer getJSON_POJO()
	{
		Customer customer = new Customer();
		return customer;
	}

	@PostMapping("/bankPost")
	public ResponseEntity<Object> create(@RequestBody Customer customer)
	{
		try
		{
			Customer customer1 = customerService.create(customer);
			Message message = new Message("Data Added..!!",customer1);
			return Response.generateResponse("Data Addition = Success!!",true,HttpStatus.CREATED, message);

		}
		catch (Exception ex) 
		{
			logger.error("Error while Create all category data: {}", ex.getMessage());
			return Response.generateResponse("Check Server Error..!!",false,HttpStatus.BAD_REQUEST,null);
		}

	}

	@PutMapping("/deptPut")
	public ResponseEntity<Object> update(Long id,@RequestBody Customer customer)
	{
		try
		{
			Customer customer1 = customerService.update(id,customer);
			Message msg = new Message("DATA UPDATED..!!",customer1);
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
			Page<Customer> customerList = customerService.read(pageable);
			Message message = new Message("Data Readed..!!",customerList);
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
			customerService.delete(id);
			Message message = new Message("Data Deleted..!!");
			return Response.generateResponse("Data Deletion = Success",true,HttpStatus.GONE, message);
		}
		catch(Exception ex)
		{
			logger.error("Error while Delete category data: {}", ex.getMessage());
			return Response.generateResponse("Check Client/Server Error..!!",false,HttpStatus.BAD_GATEWAY,null);
		}
	}


}
