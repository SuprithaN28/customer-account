package com.Assignment.CustomerAccount.config;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtOncePerRequestFilter extends OncePerRequestFilter{

	
	
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().equals("/api/token")) {
            filterChain.doFilter(request, response);
        } else {
            String requestTokenHeader = request.getHeader(AUTHORIZATION);
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                try {
                    String token = requestTokenHeader.substring("Bearer " .length());
                    Algorithm algorithm = Algorithm.HMAC256("secret" .getBytes(StandardCharsets.UTF_8));
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = jwtVerifier.verify(token);

                    String username = decodedJWT.getSubject();
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
               
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                  //  log.info("Token retrieved successfully!");
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    //log.error("Error generating token {} ", e.getMessage());
                    response.setHeader("error", e.getMessage());
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);

                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put("error_message", e.getMessage());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                   // log.error("doFilterInternal {}", e.getMessage());
                    new ObjectMapper().writeValue(response.getOutputStream(), errorMap);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }

    }


}

