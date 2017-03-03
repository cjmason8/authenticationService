package au.com.mason.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.mason.authservice.service.SessionTokenService;

@RestController
public class AuthenticateController {
	
	@Autowired
	private SessionTokenService sessionTokenService;

	@CrossOrigin(origins = { "http://localhost:4200", "http://80.241.221.122:8083" })
	@RequestMapping(value = "/authenticate/{token}", method = RequestMethod.GET, produces = "application/json")
    String login(@PathVariable String token) {
		if (sessionTokenService.validateToken(token)) {
			return "{\"tokenStatus\":\"valid\"}";
		}
		else {
			return "{\"tokenStatus\":\"invalid\"}";
		}
    }
}
