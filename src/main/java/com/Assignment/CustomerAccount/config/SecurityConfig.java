package com.Assignment.CustomerAccount.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Autowired
	private  AuthenticationConfiguration configuration;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.exceptionHandling().authenticationEntryPoint(
				(req, res, ex) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage()));
		//http.authorizeRequests().antMatchers("/cart/**").permitAll().anyRequest().authenticated();
		http.authorizeRequests().antMatchers("/api/token").permitAll().anyRequest().authenticated();
		http.addFilterBefore(new JwtOncePerRequestFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}

