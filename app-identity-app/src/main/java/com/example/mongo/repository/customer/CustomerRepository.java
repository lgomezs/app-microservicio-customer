package com.example.mongo.repository.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.mongo.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>{

}
