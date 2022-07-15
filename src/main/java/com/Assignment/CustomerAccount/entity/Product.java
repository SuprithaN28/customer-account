package com.Assignment.CustomerAccount.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


public class Product {
	

	private int prdId;
	private String prdName;
	private String category;
	private int quantity;
	private Integer price;
	private Integer rating;
	private boolean isAvailable;
	
	
	public Product() {}
	
	public Product(int prdId, String prdName, String category, int quantity, Integer price, Integer rating,
			boolean isAvailable) {
		super();
		this.prdId = prdId;
		this.prdName = prdName;
		this.category = category;
		this.quantity = quantity;
		this.price = price;
		this.rating = rating;
		this.isAvailable = isAvailable;
	}
	
	public int getPrdId() {
		return prdId;
	}
	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
	@Override
	public String toString() {
		return "Product [prdId=" + prdId + ", prdName=" + prdName + ", category=" + category + ", quantity=" + quantity
				+ ", price=" + price + ", rating=" + rating + ", isAvailable=" + isAvailable + "]";
	}
	
	
	

}
