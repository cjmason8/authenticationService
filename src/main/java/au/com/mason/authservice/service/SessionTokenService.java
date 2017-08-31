package au.com.mason.authservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.mason.authservice.dao.SessionTokenDao;
import au.com.mason.authservice.domain.SessionToken;
import au.com.mason.authservice.domain.User;

@Component
public class SessionTokenService {
	
	@Autowired
	private SessionTokenDao sessionTokenDao;
	
	public SessionToken validateToken(String token) {
		SessionToken sessionToken = sessionTokenDao.get(token);
		
    	if (sessionToken != null && LocalDateTime.now().isBefore(sessionToken.getExpiryDateTime())) {
    		return sessionToken;
    	}
    	
    	return null;
	}
	
	public SessionToken createSessionToken(User user) {
		SessionToken sessionToken = new SessionToken(user);
		sessionTokenDao.create(sessionToken);
		
		return sessionToken;
	}

}
