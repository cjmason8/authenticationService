package au.com.mason.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.mason.authservice.dto.LoginInput;
import au.com.mason.authservice.service.UserApplicationService;

@RestController
public class UserNameExistsController {
	
	@Autowired
	private UserApplicationService userService;
	
	@RequestMapping(value = "/userNameExists", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    String login(@RequestBody LoginInput loginInput) {
		if (userService.userNameExists(loginInput)) {
			return "{\"userNameExists\":\"true\"}";
		}
		else {
			return "{\"userNameExists\":\"false\"}";
		}
    }
}
