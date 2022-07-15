package com.Assignment.CustomerAccount.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Assignment.CustomerAccount.controller.AccountController;
import com.Assignment.CustomerAccount.entity.Cart;
import com.Assignment.CustomerAccount.entity.CheckoutCart;
import com.Assignment.CustomerAccount.entity.DiscountDetails;
import com.Assignment.CustomerAccount.entity.PaymentDetails;
import com.Assignment.CustomerAccount.entity.Product;
import com.Assignment.CustomerAccount.entity.ProductsInCheckout;
import com.Assignment.CustomerAccount.repository.CartRepository;
import com.Assignment.CustomerAccount.repository.DiscountRepository;
import com.Assignment.CustomerAccount.repository.PaymentRepository;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	DiscountRepository discountRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	ProductService productService;
	
	private static final Logger logger = LoggerFactory.getLogger(CartService.class);

	public void addToCart(int cusId, int pid, int quantity) {
		if (isProductAvailable(pid, quantity)) {
			Cart cartItems = new Cart();
			cartItems.setCusId(cusId);
			cartItems.setPrdId(pid);
			cartItems.setQuantity(quantity);
			cartRepository.save(cartItems);
			updateProductQuantity(pid, quantity);

		}
	}

	public boolean isProductAvailable(int pid, int quantity) {
		Product p = productService.fetchProduct(pid);
		if (quantity <= p.getQuantity()) {
			return true;
		}
		return false;

	}

	public void updateProductQuantity(int pid, int quantity) {
		Product p = productService.fetchProduct(pid);
		if (p.getQuantity() == quantity) {
			productService.updateAvailabilty(pid);
		}
		productService.updateQuantity(p.getPrdId(), (p.getQuantity() - quantity));

	}

	public CheckoutCart checkoutCart(int cus_id) {
		List<Cart> cartItems = cartRepository.findAllBycustomer(cus_id);
		Map<Integer, Integer> productList = new HashMap<>();
		cartItems.forEach(Cart -> {
			if (productList.containsKey(Cart.getPrdId())) {
				productList.put(Cart.getPrdId(), productList.get(Cart.getPrdId()) + Cart.getQuantity());
			} else {
				productList.put(Cart.getPrdId(), Cart.getQuantity());
			}

		});

		Double total = 0.0;
		Map<ProductsInCheckout, Integer> productQuantity = new HashMap<>();
		for (Integer id : productList.keySet()) {
			Product p = productService.fetchProduct(id);
			ProductsInCheckout p1 = new ProductsInCheckout(p.getPrdId(), p.getPrdName(), p.getCategory(), p.getPrice());
			productQuantity.put(p1, productList.get(id));
			total += p.getPrice() * productList.get(id);
		}
		logger.info("Total bill for customer "+cus_id+" is "+total);
		double defaultDiscount = 0;
		for (ProductsInCheckout p : productQuantity.keySet()) {

			double amount = p.getPrice() * productQuantity.get(p);
			if (productQuantity.get(p) > 5) {
				DiscountDetails d = discountRepository.findByMinQuantity(productQuantity.get(p));
				System.out.println(d);
				double discountAmount = (d.getDiscountPercentage() / 100.00) * amount;
				defaultDiscount += discountAmount;
				logger.info("Discount provided for customer "+cus_id+" is "+defaultDiscount);
			}
		}
		logger.info("Amount to be paid by "+cus_id+" after discount applied "+(total - defaultDiscount));
		return new CheckoutCart(productQuantity, total - defaultDiscount);

	}

	public void updateCheckout(int cusId) {
		List<Cart> updatePending = cartRepository.findBycusId(cusId);
		for (Cart i : updatePending) {
			cartRepository.updateCheckedout(i.getCartId());
		}

	}

	public CheckoutCart checkoutCartWithDiscount(int cusId, String code) {
		CheckoutCart withoutDiscount = checkoutCart(cusId);
		DiscountDetails d = discountRepository.findByPromoCode(code);
		double cal = (d.getDiscountPercentage() / 100.00);
		double discountAmount = (cal * withoutDiscount.getTotalAfterDiscount());

		withoutDiscount.setTotalAfterDiscount(withoutDiscount.getTotalAfterDiscount() - discountAmount);
		logger.info("Discount provided for customer "+cusId+" is "+discountAmount);
		logger.info("Amount to be paid by "+cusId+" after discount applied "+(withoutDiscount.getTotalAfterDiscount() - discountAmount));
		return withoutDiscount;
	}

	public void updatePaymentDetails(int cusId, double amount) {
		PaymentDetails pd = new PaymentDetails();
		pd.setAmount(amount);
		pd.setCus_id(cusId);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		pd.setMyTimestamp(timestamp);
		paymentRepository.save(pd);
	}

}
