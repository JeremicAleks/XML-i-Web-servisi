package com.centralapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CentralApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralApiApplication.class, args);
	}
	
}
