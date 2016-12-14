package au.com.mason.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.mason.authservice.domain.User;
import au.com.mason.authservice.dto.CreateUserInput;
import au.com.mason.authservice.service.UserApplicationService;

@RestController
public class CreateUserController {
	
	@Autowired
	private UserApplicationService userApplicationService;
	
	@RequestMapping(value = "/userApplication", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    String login(@RequestBody CreateUserInput createUserInput) {
		
		User user = userApplicationService.createUser(createUserInput);
		
		if (user != null) {
			return "{\"status\":\"success\"}";
		}
		else {
			return "{\"status\":\"failed\"}";
		}
    }
}
