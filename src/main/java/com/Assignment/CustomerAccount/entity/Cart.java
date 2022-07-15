package com.Assignment.CustomerAccount.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartId", updatable = false, nullable = false)
	private int cartId;
	private int prdId;
	private int cusId;
	private int quantity;
	@Column(name="checked_out")
	private boolean checkedOut;

	public boolean isCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	public Cart() {
	}

	public Cart(int cartId, int prdId, int cusId, int quantity, boolean checkedOut) {
		super();
		this.cartId = cartId;
		this.prdId = prdId;
		this.cusId = cusId;
		this.quantity = quantity;
		this.checkedOut = checkedOut;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getPrdId() {
		return prdId;
	}

	public void setPrdId(int prdId) {
		this.prdId = prdId;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", prdId=" + prdId + ", cusId=" + cusId + ", quantity=" + quantity
				+ ", checkedOut=" + checkedOut + "]";
	}

}
