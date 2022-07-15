package com.Assignment.CustomerAccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CustomerAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAccountApplication.class, args);
	}

}
