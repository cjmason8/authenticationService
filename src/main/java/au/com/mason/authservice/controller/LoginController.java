package au.com.mason.authservice.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.mason.authservice.domain.SessionToken;
import au.com.mason.authservice.domain.UserApplication;
import au.com.mason.authservice.dto.LoginInput;
import au.com.mason.authservice.service.SessionTokenService;
import au.com.mason.authservice.service.UserApplicationService;

@RestController
public class LoginController {
	
	private static final Logger LOGGER = LogManager.getLogger(LoginController.class);
	
	@Autowired
	private UserApplicationService userService;
	
	@Autowired
	private SessionTokenService sessionTokenService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    String login(@RequestBody LoginInput loginInput) {
		LOGGER.info("{\"message\":\"userName - " + loginInput.getUserName() + ", password - " + loginInput.getPassword() + ", application - " + loginInput.getApplicationType() + "}");
		UserApplication validatedUser = userService.validateUser(loginInput);
		if (validatedUser != null) {
			SessionToken sessionToken = sessionTokenService.createSessionToken(validatedUser.getUser());
			return "{\"loginStatus\":\"success\",\"user\":\"" + validatedUser.getUser().getUserName() + "\", \"token\":\"" + sessionToken.getToken() + "\", \"roles\":\"" + StringUtils.join(validatedUser.getRoles(), ',') + "\"}";
		}
		else {
			LOGGER.info("Login failed for user - " + loginInput.getUserName() + ", password - " + loginInput.getPassword() + ", application - " + loginInput.getApplicationType());
			return "{\"loginStatus\":\"failed\",\"user\":\"" + loginInput.getUserName() + "\"}";
		}
    }
}
