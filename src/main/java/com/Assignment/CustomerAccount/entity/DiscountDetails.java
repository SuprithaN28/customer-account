package com.Assignment.CustomerAccount.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="discount_details")
public class DiscountDetails {

	@Id
	private int disId;
	private int minQuantity;
	private String promoCode;
	private int discountPercentage;
	
	public DiscountDetails() {}
	

	public int getDisId() {
		return disId;
	}
	public void setDisId(int disId) {
		this.disId = disId;
	}
	public int getMinQuantity() {
		return minQuantity;
	}
	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}

	public int getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage = discountPercentage;
	}


	public String getPromoCode() {
		return promoCode;
	}


	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}


	@Override
	public String toString() {
		return "DiscountDetails [disId=" + disId + ", minQuantity=" + minQuantity + ", promoCode=" + promoCode
				+ ", discountPercentage=" + discountPercentage + "]";
	}


	public DiscountDetails(int disId, int minQuantity, String promoCode, int discountPercentage) {
		super();
		this.disId = disId;
		this.minQuantity = minQuantity;
		this.promoCode = promoCode;
		this.discountPercentage = discountPercentage;
	}

	
}
