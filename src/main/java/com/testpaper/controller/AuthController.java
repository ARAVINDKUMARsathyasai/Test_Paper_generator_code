package com.testpaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testpaper.model.LoginRequest;
import com.testpaper.model.User;
import com.testpaper.service.UserService;

/**
 * The AuthController class represents the RESTful API controller for handling user authentication.
 * It provides endpoints for user login and authentication.
 * 
 * @see LoginRequest
 * @see User
 * @see UserService
*/
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:3000")
public class AuthController {
  
	/**
	 * The UserService is responsible for handling user-related operations.
	 * 
	 * @see UserService
    */
  @Autowired
  private UserService userService;

  /**
   * Authenticates a user based on the provided login request.  
   * 
   * @param loginRequest The login request object containing user credentials.  
   * @return ResponseEntity containing the authenticated user or an error message.
   * 
   * @see LoginRequest
   * @see User
   * @see org.springframework.http.ResponseEntity
   * @see org.springframework.http.HttpStatus
  */
  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    // Retrieve user from the database based on the email
    User user = userService.getUserByEmail(loginRequest.getEmail());

    // Check if the user exists and the password matches
    if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
       if(user.isChecked())
    	   return ResponseEntity.ok(user);
       else
    	   return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Denaied");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
  }
}

