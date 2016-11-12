package au.com.mason.expenseManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.mason.expenseManager.domain.SessionToken;
import au.com.mason.expenseManager.domain.User;
import au.com.mason.expenseManager.service.SessionTokenService;
import au.com.mason.expenseManager.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SessionTokenService sessionTokenService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    String login(@RequestBody User user) {
		User validatedUser = userService.validateUser(user);
		if (validatedUser != null) {
			SessionToken sessionToken = sessionTokenService.createSessionToken(validatedUser);
			return "{\"loginStatus\":\"success\",\"user\":\"" + validatedUser.getUserName() + "\", \"token\":\"" + sessionToken.getToken() + "\"}";
		}
		else {
			return "{\"loginStatus\":\"failed\",\"user\":\"" + user.getUserName() + "\"}";
		}
    }
}
