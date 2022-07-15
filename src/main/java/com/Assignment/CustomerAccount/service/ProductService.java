package com.Assignment.CustomerAccount.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Assignment.CustomerAccount.entity.Product;
import com.Assignment.CustomerAccount.entity.Products;

@Service
public class ProductService {
	
	String productServiceUrl = "http://localhost:8082/products";
	
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public Products fetchProductList() {
	
		
		ResponseEntity<Products> response = restTemplate.getForEntity(productServiceUrl+"/fetchProducts", Products.class);
		return response.getBody();
		
	}
	
	public Product fetchProduct(int id) {
		
		ResponseEntity<Product> response = restTemplate.getForEntity(productServiceUrl+"/fetchProductById/"+id, Product.class);
		return response.getBody();
		
	}
	
	public void updateAvailabilty(int id) {
		
		ResponseEntity<Boolean> response = restTemplate.exchange(productServiceUrl+"/setAvailability/"+id,HttpMethod.PUT,null, Boolean.class);
		
		
	}
	
	public void updateQuantity(int id,int quantity) {
		
		ResponseEntity<Boolean> response = restTemplate.exchange(productServiceUrl+"/setQuantity/"+id+"/"+quantity,HttpMethod.PUT,null, Boolean.class);
	
		
	}
}
