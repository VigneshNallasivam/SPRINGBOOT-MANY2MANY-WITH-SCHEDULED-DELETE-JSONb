package com.spring.map.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.map.model.Customer;
import com.spring.map.repository.CustomerRepository;

@Service
public class CustomerService 
{
	@Autowired
	CustomerRepository customerRepository;

	public Customer create(Customer customer) 
	{
		return customerRepository.save(customer);
	}
	
	public Customer update(Long id,Customer customer) throws Exception 
	{
		Customer customer2 = customerRepository.findById(id).get();
		if(customerRepository.findById(id).isPresent())
		{
			customer2.setCustomerName(customer.getCustomerName());
			customer2.setCustomerAccNo(customer.getCustomerAccNo());
			customerRepository.save(customer2);
			return customer2;
		}
		else
		{
			throw new Exception("ID NOT-FOUND..!!");
		}
		
	}

	public Page<Customer> read(Pageable pageable) throws Exception 
	{
		return customerRepository.findAllByPagination(pageable);
	}

	public void delete(Long id) throws Exception
	{
		if(customerRepository.findById(id).isPresent())
		{
			customerRepository.deleteById(id);
		}
		else
		{
			throw new Exception("ID NOT-FOUND..!!");
		}

	}
}
