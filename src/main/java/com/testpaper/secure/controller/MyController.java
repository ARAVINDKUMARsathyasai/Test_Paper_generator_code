package com.testpaper.secure.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.testpaper.model.User;
import com.testpaper.repository.UserRepository;

/**
 * The MyController class represents a RESTful API controller that handles various endpoints related to user roles and authorization.
 * It provides endpoints for home, user, and admin pages, as well as an update page that requires the 'admin' authority.
*/
@RestController
@CrossOrigin
public class MyController {
	/**
	 * The UserRepository is responsible for interacting with the database and performing CRUD operations on the User entity.
	 * 
	 * @see com.testpaper.repository.UserRepository
    */
	@Autowired
	private UserRepository userRepo;
	
	/**
	 * Retrieves the home page.
	 * 
	 * @return The HTML content for the home page.
    */
	@GetMapping("/")
	public String home() {
		return "<h1> Home Page</h1>";
	}
	
	/**
	 * Retrieves the user page.
	 * @return The HTML content for the user page.
    */
	@GetMapping("/user")
	public String user() {
		return "<h1> User Page</h1>";
	}
	
	/**
	 * Retrieves the admin page.
	 * @return The HTML content for the admin page.
    */
	@GetMapping( value = "/admin",produces = {"application/json","application/xml"})
	public String admin() {
		return "<h1> Admin Page</h1>";
	}
	
	/**
	 * Retrieves the update page.
	 * Requires the 'admin' authority.
	 * 
	 * @param p The Principal object representing the authenticated user.
	 * @return The HTML content for the update page, including the username and user ID.
	 * 
	 * @see Principal
	 * @see User
	 * @see com.testpaper.model.User
	 * @see com.testpaper.repository.UserRepository#findByUsername(String)
    */
	@GetMapping("/update")
	@PreAuthorize("hasAuthority('admin')")
	//@PreAuthorize("hasAnyRole('admin','user')")
	public String update(Principal p) {
		User user=userRepo.findByUsername(p.getName());

		return "<h1> Update Page "+p.getName()+ " and user id is "+ user.getId()+" </h1>";
	}
	
	/**
	 * Retrieves the user1ex page.
	 * Requires the 'user' authority.
	 * 
	 * @param p The Principal object representing the authenticated user.
	 * @return The HTML content for the user1ex page, including the username and user ID.
	 * 
	 * @see Principal
	 * @see User
	 * @see com.testpaper.model.User
	 * @see com.testpaper.repository.UserRepository#findByUsername(String)
    */
	@GetMapping("/user1")
	@PreAuthorize("hasAuthority('user')")
	public String user1(Principal p) {
		User user=userRepo.findByUsername(p.getName());
		return "<h1> User1ex Page "+p.getName()+" and user id is "+ user.getId()+" </h1>";
	}
	
}