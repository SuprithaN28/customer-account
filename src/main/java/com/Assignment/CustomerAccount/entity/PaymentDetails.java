package com.Assignment.CustomerAccount.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PaymentDetails {

	@Id
	private int paymentId;
	private double amount;
	private int cus_id;
	@GeneratedValue()
	private Timestamp myTimestamp;
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getCus_id() {
		return cus_id;
	}
	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}
	public Timestamp getMyTimestamp() {
		return myTimestamp;
	}
	public void setMyTimestamp(Timestamp myTimestamp) {
		this.myTimestamp = myTimestamp;
	}
	@Override
	public String toString() {
		return "PaymentDetails [paymentId=" + paymentId + ", amount=" + amount + ", cus_id=" + cus_id + ", myTimestamp="
				+ myTimestamp + "]";
	}
	public PaymentDetails(int paymentId, double amount, int cus_id, Timestamp myTimestamp) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.cus_id = cus_id;
		this.myTimestamp = myTimestamp;
	}
	public PaymentDetails() {
		
	}
	
	
	
}
