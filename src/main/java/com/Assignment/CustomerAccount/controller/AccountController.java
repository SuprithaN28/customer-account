package com.Assignment.CustomerAccount.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment.CustomerAccount.entity.CheckoutCart;
import com.Assignment.CustomerAccount.entity.Customer;
import com.Assignment.CustomerAccount.service.CartService;
import com.Assignment.CustomerAccount.service.CustomerService;

@RestController
@RequestMapping("/cart/")
public class AccountController {

	@Autowired
	CartService cartService;
	

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	CustomerService customerService;

	@PostMapping("addToCart/{pid}/{quantity}")
	public String addCart(Authentication auth, @PathVariable int pid, @PathVariable int quantity) {
		Customer customer = customerService.fetchCustomer(auth.getName());
		cartService.addToCart(customer.getCusId(), pid, quantity);
		logger.info("Adding "+quantity+" products to "+auth.getName()+" cart");
		return quantity + " products with ID " + pid + " added to your cart sucessfully";

	}

	@RequestMapping({ "/checkout", "/checkout/{code}" })
	public CheckoutCart checkout(Authentication auth, @PathVariable(name = "code", required = false) String code) {
		Customer customer = customerService.fetchCustomer(auth.getName());
		
		if (code != null && !code.isBlank()) {
			CheckoutCart shopping = cartService.checkoutCartWithDiscount(customer.getCusId(), code);
			return shopping;
		} else {
			logger.info(auth.getName()+" used "+code+" during checkout");
			CheckoutCart shopping = cartService.checkoutCart(customer.getCusId());
			return shopping;
		}

	}

	@RequestMapping({ "/checkout/pay/{amount}", "/checkout/{code}/pay/{amount}" })
	public String payment(Authentication auth, @PathVariable int amount,
			@PathVariable(name = "code", required = false) String code) {
		Customer customer = customerService.fetchCustomer(auth.getName());

		CheckoutCart shopping = null;
		if (code != null) {
			logger.info(auth.getName()+" used "+code+" during checkout");
			shopping = cartService.checkoutCartWithDiscount(customer.getCusId(), code);

		} else {
			shopping = cartService.checkoutCart(customer.getCusId());

		}

		if (shopping.getTotalAfterDiscount().intValue() == amount) {
			cartService.updateCheckout(customer.getCusId());
			cartService.updatePaymentDetails(customer.getCusId(), amount);
			logger.info(auth.getName()+" paid "+amount+" to HashKart sucessfully..!!");
			return "Payment Successfull..!!";
		}
		logger.info("payment failed for "+auth.getName());
		logger.info("expected amount: "+shopping.getTotalAfterDiscount().intValue());
		logger.info("amount paid by customer: "+amount);
		return "Payment Failed.Please try again";
	}

}
