package com.testpaper.secure.config;

import java.io.IOException;
/*
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import com.testpaper.secure.config.service.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The JwtRequestFilter class is a filter that intercepts incoming requests and processes JWT authentication.
 * It checks the Authorization header for a JWT token, validates it, and sets the authentication context for the request.
*/
@Component
@CrossOrigin
public class JwtRequestFilter extends OncePerRequestFilter {

	/**
	 * The JwtUserDetailsService is responsible for retrieving user details required for JWT authentication.
	 * It implements the UserDetailsService interface provided by Spring Security.
    */
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	/**
	 * The JwtTokenUtil is a utility class that provides various operations related to JWT tokens,
	 * such as token generation, validation, and extracting information from the token.
    */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 * Performs the filtering logic for each incoming request.
	 *
	 * @param request  The HTTP servlet request.
	 * @param response The HTTP servlet response.
	 * @param chain    The filter chain.
	 * @throws ServletException If an error occurs during the filter processing.
	 * @throws IOException      If an I/O error occurs during the filter processing.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
			System.out.println("do Filter ......");
		final String requestTokenHeader = request.getHeader("Authorization");
			System.out.println("Token from client : "+requestTokenHeader);
		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		//Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		System.out.println("----chain do filtering ....");
		chain.doFilter(request, response);
	}
}