package com.Assignment.CustomerAccount.entity;

import java.util.Map;


public class CheckoutCart {

	private Map<ProductsInCheckout, Integer> shoppingList;
	private Double totalAfterDiscount;

	public CheckoutCart() {
 
	}



	public CheckoutCart(Map<ProductsInCheckout, Integer> shoppingList, Double totalAfterDiscount) {
		super();
		this.shoppingList = shoppingList;
		this.totalAfterDiscount = totalAfterDiscount;
	}



	@Override
	public String toString() {
		return "CheckoutCart [shoppingList=" + shoppingList + ", totalAfterDiscount=" + totalAfterDiscount + "]";
	}



	public Double getTotalAfterDiscount() {
		return totalAfterDiscount;
	}



	public void setTotalAfterDiscount(Double totalAfterDiscount) {
		this.totalAfterDiscount = totalAfterDiscount;
	}



	public Map<ProductsInCheckout, Integer> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(Map<ProductsInCheckout, Integer> shoppingList) {
		this.shoppingList = shoppingList;
	}



	
	

}
