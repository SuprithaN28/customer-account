package com.Assignment.CustomerAccount;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;



import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.Assignment.CustomerAccount.controller.AccountController;
import com.Assignment.CustomerAccount.entity.Customer;
import com.Assignment.CustomerAccount.service.CartService;
import com.Assignment.CustomerAccount.service.CustomerService;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;


@WebMvcTest(AccountController.class)
public class AccountControllerTest {
	

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;
	
//	@Before
//    public void setup() {
//		mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity()) // sets up Spring Security with MockMvc
//                .build();
//    }
//	
	
	
	@MockBean
	CartService cartService;
	
	@MockBean
	CustomerService customerService;
	
	

	@Test
	@WithMockUser(username = "suprin@gmail")
	void addtocart() throws Exception {
	    Customer customer = new Customer(1, "suprin","suprin@gmail", "password", "test", 677676);
        
		  when(customerService.fetchCustomer(any())).thenReturn(customer);
		  doNothing().when(cartService).addToCart(anyInt(), anyInt(), anyInt());

		mockMvc.perform(MockMvcRequestBuilders.post("/cart/addToCart/5/2").with(csrf()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
