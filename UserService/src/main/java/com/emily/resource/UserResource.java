package com.emily.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.emily.entity.User;
import com.emily.service.UserService;

@RestController
public class UserResource {
	
	/* creating an instance of the userService to access loginCheck.. method
	will automatically use UserServiceImpl */
	@Autowired
	private UserService userService;
	
	/* Creating an API path to retrieve (GET) a user from the database
	 * Will return either a user, or null in case of no user or error
	 * User object can be mapped to a JSON format
	 */
	@GetMapping(path = "/users/{userEmail}/{userPassword}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User loginCheckResource(@PathVariable("userEmail") String userEmail, @PathVariable("userPassword") String userPassword) {
			return userService.loginCheck(userEmail, userPassword);
	}

}
