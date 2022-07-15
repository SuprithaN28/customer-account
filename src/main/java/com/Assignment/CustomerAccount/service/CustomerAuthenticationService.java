package com.Assignment.CustomerAccount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Assignment.CustomerAccount.entity.Customer;

@Service
public class CustomerAuthenticationService implements UserDetailsService {

	@Autowired
	private CustomerService customerService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Customer customer = customerService.fetchCustomer(email);
		CustomerAuthentication userDetails = null;
		if (customer != null) {
			userDetails = new CustomerAuthentication(); 
			userDetails.setCustomer(customer);
		}
		System.out.println(userDetails.getUsername()+" "+userDetails.getPassword());
		return userDetails;
	}

}