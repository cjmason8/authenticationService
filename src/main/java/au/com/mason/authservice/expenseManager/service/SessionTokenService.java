package au.com.mason.expenseManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.mason.expenseManager.dao.SessionTokenDao;
import au.com.mason.expenseManager.domain.SessionToken;
import au.com.mason.expenseManager.domain.User;

@Component
public class SessionTokenService {
	
	@Autowired
	private SessionTokenDao sessionTokenDao;
	
	public boolean validateToken(String token) {
		return sessionTokenDao.validateSessionToken(token);
	}
	
	public SessionToken createSessionToken(User user) {
		SessionToken sessionToken = new SessionToken(user);
		sessionTokenDao.create(sessionToken);
		
		return sessionToken;
	}

}
