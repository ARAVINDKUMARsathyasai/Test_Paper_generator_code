package com.testpaper.secure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.testpaper.secure.config.JwtTokenUtil;
import com.testpaper.secure.config.model.JwtRequest;
import com.testpaper.secure.config.model.JwtResponse;
import com.testpaper.secure.config.model.UserDto;
import com.testpaper.secure.config.service.JwtUserDetailsService;

/**
 * The JwtAuthenticationController class handles the authentication-related endpoints for the application.
 * It allows users to login and register, generating and validating JWT tokens.
*/
@RestController
@CrossOrigin
public class JwtAuthenticationController {

	/**
	 * The authentication manager used for user authentication.
    */
    private final AuthenticationManager authenticationManager;
    /**
     * The JWT token utility for generating and validating JWT tokens.
    */
    private final JwtTokenUtil jwtTokenUtil;
    /**
     * The user details service for retrieving user details.
    */
    private final JwtUserDetailsService userDetailsService;

    /**
     * Constructs a new JwtAuthenticationController with the specified dependencies.
     * 
     * @param authenticationManager The authentication manager.
     * @param jwtTokenUtil The JWT token utility.
     * @param userDetailsService The user details service.
    */
    @Autowired
    public JwtAuthenticationController(
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            JwtUserDetailsService userDetailsService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Creates an authentication token for the user with the provided credentials.
     * 
     * @param authenticationRequest The authentication request containing user credentials.
     * @return The ResponseEntity containing the JWT token and user roles upon successful authentication.
     * @throws Exception if an error occurs during authentication.
     * @see JwtRequest
     * @see JwtResponse
    */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        boolean isAuthenticated = authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        if (!isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        String roles = userDetailsService.getRoles();
        return ResponseEntity.ok(new JwtResponse(token, roles));
    }

    /**
     * Registers a new user with the provided user details.
     * 
     * @param user The user details to be registered.
     * @return The ResponseEntity containing the saved user details upon successful registration.
     * @throws Exception if an error occurs during user registration.
     * @see UserDto
    */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
    	user.setRole("USER");
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    /**
     * Authenticates a user with the provided username and password.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @return True if the user is successfully authenticated, false otherwise.
    */
    private boolean authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return true;
        } catch (DisabledException e) {
            return false;
        } catch (BadCredentialsException e) {
            return false;
        }
    }
}
