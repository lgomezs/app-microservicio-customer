package com.example.mongo.azure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongo.customer.service.CustomerService;
import com.example.mongo.model.Customer;

@RestController()
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/listCustomer")
    @ResponseBody
    public List<Customer> listCustomer() {
		 return customerService.findAll();
	}
}
