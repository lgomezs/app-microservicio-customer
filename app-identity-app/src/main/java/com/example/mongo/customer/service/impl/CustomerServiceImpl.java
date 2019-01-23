package com.example.mongo.customer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mongo.customer.service.CustomerService;
import com.example.mongo.model.Customer;
import com.example.mongo.repository.customer.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> findAll() {	
		return customerRepository.findAll();
	}
	
}
