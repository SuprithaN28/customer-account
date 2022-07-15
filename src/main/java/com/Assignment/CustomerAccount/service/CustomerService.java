package com.Assignment.CustomerAccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Assignment.CustomerAccount.entity.Customer;

@Service
public class CustomerService {
	
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public Customer fetchCustomer(String email) {
		
		ResponseEntity<Customer> response = restTemplate.getForEntity("http://localhost:8081/customer/findbyemail/"+email, Customer.class);
		return response.getBody();
		
	}

}
