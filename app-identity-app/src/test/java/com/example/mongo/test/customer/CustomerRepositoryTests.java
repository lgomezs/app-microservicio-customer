package com.example.mongo.test.customer;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.mongo.customer.service.CustomerService;
import com.example.mongo.model.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerService customerService;

    Customer dave, oliver, carter;

    @Test
    public void findAll() {
        List<Customer> result = customerService.findAll();

        result.stream().forEach(System.out::println);
        
    }
}