package com.emily.userservice.resource;

import com.emily.userservice.entity.User;
import com.emily.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8086")
public class UserResource {

    /* creating an instance of the userService to access loginCheck method
	will automatically use UserServiceImpl */
    @Autowired
    UserService userService;

    /* Creating an API path to retrieve (GET) a user from the database
     * Will return either a user, or null in case of no user or error
     * User object can be mapped to a JSON format
     */
    @GetMapping("/{userEmail}/{userPassword}")
    public User loginCheckResource(@PathVariable("userEmail") String userEmail, @PathVariable("userPassword") String userPassword) {
        return userService.loginCheck(userEmail, userPassword);
    }
}
