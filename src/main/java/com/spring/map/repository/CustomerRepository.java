package com.spring.map.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.map.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

	@Query("SELECT c FROM Customer c")
	Page<Customer> findAllByPagination(Pageable pageable);

}
