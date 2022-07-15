package com.Assignment.CustomerAccount.entity;

public class ProductsInCheckout {
	
	
	private int prdId;
	private String prdName;
	private String category;
	private Integer price;
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public ProductsInCheckout(int prdId, String prdName, String category, Integer price) {
		super();
		this.prdId = prdId;
		this.prdName = prdName;
		this.category = category;
		this.price = price;
	}
	@Override
	public String toString() {
		return "ProductsInCheckout [prdId=" + prdId + ", prdName=" + prdName + ", category=" + category + ", price="
				+ price + "]";
	}
	
	

}
