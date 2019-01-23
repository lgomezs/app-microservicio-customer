package com.example.mongo.repository.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.mongo.config.IdentityMongoConfiguration;


@Configuration
@EnableMongoRepositories(basePackages = "com.example.mongo.repository.customer", 
		mongoTemplateRef = "customerMongoTemplate")
public class CustomerMongoConfiguration {

	@Autowired
	IdentityMongoConfiguration identityMongoConfiguration;

	@Value("${identity.mongodb.dbname}")
	String dbName;

	@Value("${identity.mongodb.collectioncustomer}")
	String dbCollectionCustomer;

	@Bean(name = "customerMongoTemplate")
	public MongoTemplate customerMongoTemplate() throws Exception {
		return new MongoTemplate(customerFactory());
	}

	public MongoDbFactory customerFactory() throws Exception {
		return new SimpleMongoDbFactory(identityMongoConfiguration.mongoClient(dbCollectionCustomer), dbName);
	}
	
}
