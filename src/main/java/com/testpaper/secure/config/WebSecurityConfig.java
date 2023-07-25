package com.testpaper.secure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The WebSecurityConfig class is responsible for configuring the web security of the application.
 * It sets up authentication, authorization, password encoding, and token-based authentication using JWT.
 * 
 * @see JwtAuthenticationEntryPoint
 * @see UserDetailsService
 * @see JwtRequestFilter
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	/**
	 * The JWT authentication entry point handles authentication errors and returns appropriate responses.
	 * 
	 * @see JwtAuthenticationEntryPoint
    */
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	/**
	 * The user details service for JWT authentication.
	 * 
	 * @see UserDetailsService
    */
	@Autowired
	private UserDetailsService jwtUserDetailsService;

	/**
	 * The JWT request filter for token-based authentication.
	 * 
	 * @see JwtRequestFilter
    */
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	/**
	 * Configures the global authentication manager to use the JWT user details service and password encoder.
	 * 
	 * @param auth The authentication manager builder.
	 * @throws Exception if an error occurs during configuration.
	 * @see UserDetailsService
	 * @see PasswordEncoder
    */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	/**
	 * Configures the password encoder to use BCrypt hashing algorithm.
	 * 
	 * @return The configured password encoder.
	 * @see PasswordEncoder
    */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Creates the authentication manager bean.
	 * 
	 * @param http The HTTP security object.
	 * @return The configured authentication manager.
	 * @throws Exception if an error occurs during configuration.
	 * 
	 * @see AuthenticationManager
    */
	 @Bean
	 public AuthenticationManager authManager(HttpSecurity http) throws Exception {
	      AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
	      return authenticationManagerBuilder.build();
	 }

	 /**
	  * Configures the HTTP security of the application.
	  * 
	  * @param httpSecurity The HTTP security object.
	  * @return The configured security filter chain.
	  * @throws Exception if an error occurs during configuration.
	  * @see HttpSecurity
	  * @see JwtAuthenticationEntryPoint
	  * @see JwtRequestFilter
	  */
    @Bean
	protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println("---Web security -- configure");
		httpSecurity.cors().and().csrf().disable()
			.authorizeHttpRequests().requestMatchers("/login", "/register","/**","/Admin/**","/question/**").permitAll().
						anyRequest().authenticated().and().
						exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
}
