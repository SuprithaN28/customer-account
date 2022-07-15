package com.Assignment.CustomerAccount.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


public class Customer {
	
	
	private int cusId;
	private String cusName;
	private String email;
	private String password;
	private String address;
	private int phoneNo;
	
	
	public Customer() {}
	
	
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Customer(int cusId, String cusName, String email, String password, String address, int phoneNo) {
		super();
		this.cusId = cusId;
		this.cusName = cusName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNo = phoneNo;
	}
	
	
	@Override
	public String toString() {
		return "Customer [cusId=" + cusId + ", cusName=" + cusName + ", email=" + email + ", password=" + password
				+ ", address=" + address + ", phoneNo=" + phoneNo + "]";
	}
	
	
	

}
