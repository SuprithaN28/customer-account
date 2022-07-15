package com.Assignment.CustomerAccount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import com.Assignment.CustomerAccount.entity.Cart;
import com.Assignment.CustomerAccount.entity.CheckoutCart;
import com.Assignment.CustomerAccount.entity.DiscountDetails;
import com.Assignment.CustomerAccount.entity.PaymentDetails;
import com.Assignment.CustomerAccount.entity.Product;
import com.Assignment.CustomerAccount.repository.CartRepository;
import com.Assignment.CustomerAccount.repository.DiscountRepository;
import com.Assignment.CustomerAccount.repository.PaymentRepository;
import com.Assignment.CustomerAccount.service.CartService;
import com.Assignment.CustomerAccount.service.ProductService;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

	@Mock
	CartRepository cartRepository;

	@Mock
	DiscountRepository discountRepository;

	@Mock
	PaymentRepository paymentRepository;

	@Mock
	ProductService productService;

	@InjectMocks
	CartService cartService;

	@Test
	void addToCart() {
		Cart cart = Mockito.mock(Cart.class);
		Product p = new Product(1, "aa", "dd", 10, 10, 10, true);
		when(cartRepository.save(any(Cart.class))).thenReturn(cart);
		when(productService.fetchProduct(anyInt())).thenReturn(p);
		cartService.addToCart(1, 1, 1);
		verify(cartRepository, times(1)).save(any(Cart.class));
	}

	@Test
	void addToCartfalse() {
		Cart cart = Mockito.mock(Cart.class);
		Product p = Mockito.mock(Product.class);
		when(productService.fetchProduct(anyInt())).thenReturn(p);

		cartService.addToCart(1, 1, 1);
		verify(cartRepository, never()).save(any(Cart.class));
	}

	@Test
	void checkoutCartWithDiscount() {
		List<Cart> cartitem = new ArrayList<>();
		Cart c1 = new Cart(0, 0, 0, 0, false);
		Cart c2 = new Cart(1, 0, 0, 0, false);
		cartitem.add(c2);
		cartitem.add(c1);

		when(cartRepository.findAllBycustomer(anyInt())).thenReturn(cartitem);

		DiscountDetails d = new DiscountDetails(1, 4, "code", 7);
		// when(discountRepository.findByMinQuantity(anyInt())).thenReturn(d);
		Product p = new Product(1, "aa", "dd", 10, 10, 10, true);
		when(productService.fetchProduct(anyInt())).thenReturn(p);
		CheckoutCart x = cartService.checkoutCart(anyInt());
		Assert.notNull(x);
	}

	@Test
	void checkoutCart() {
		List<Cart> cartitem = new ArrayList<>();
		Cart c1 = new Cart(0, 0, 0, 0, false);
		Cart c2 = new Cart(1, 0, 0, 0, false);
		cartitem.add(c2);
		cartitem.add(c1);

		when(cartRepository.findAllBycustomer(anyInt())).thenReturn(cartitem);

		DiscountDetails d = new DiscountDetails(1, 4, "code", 7);
		// when(discountRepository.findByMinQuantity(anyInt())).thenReturn(d);
		Product p = new Product(1, "aa", "dd", 10, 10, 10, true);
		when(productService.fetchProduct(anyInt())).thenReturn(p);

		CheckoutCart x = cartService.checkoutCart(anyInt());
		Assert.notNull(x);
	}

	@Test
	void updatePaymentDetails() {

		cartService.updatePaymentDetails(2, 8);
		verify(paymentRepository, times(1)).save(any(PaymentDetails.class));
	}

}
