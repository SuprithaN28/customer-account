package com.Assignment.CustomerAccount.controller;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment.CustomerAccount.entity.Authorization;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/token")
public class FetchTokenController {

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping
	public String getToken(@RequestBody Authorization auth) {
		try {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				auth.getEmail(), auth.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		UserDetails user = (UserDetails) authentication.getPrincipal();
	//	log.info("User details found : {} ", user.getUsername());
		System.out.println(user.getUsername());
		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes(StandardCharsets.UTF_8));
		String access_token = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000)).sign(algorithm);
		return access_token;
		}
		catch(BadCredentialsException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}

