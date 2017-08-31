package au.com.mason.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.mason.authservice.domain.SessionToken;
import au.com.mason.authservice.service.SessionTokenService;

@RestController
public class AuthenticateController {
	
	@Autowired
	private SessionTokenService sessionTokenService;

	@RequestMapping(value = "/authenticate/{token}", method = RequestMethod.GET, produces = "application/json")
    String login(@PathVariable String token) {
		SessionToken sessionToken = sessionTokenService.validateToken(token);
		if (sessionToken != null) {
			return "{\"tokenStatus\":\"valid\", \"user\":\"" + sessionToken.getUser().getUserName() + "\"}";
		}
		else {
			return "{\"tokenStatus\":\"invalid\"}";
		}
    }
}
