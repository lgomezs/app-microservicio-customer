package com.example.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;



@EnableAutoConfiguration
@EnableDiscoveryClient  
@SpringBootApplication
@ComponentScan({"com.example.mongo"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
    }
	
}
