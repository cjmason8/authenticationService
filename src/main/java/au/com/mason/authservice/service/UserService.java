package au.com.mason.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.mason.authservice.dao.UserDao;
import au.com.mason.authservice.domain.User;

@Component
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User validateUser(User user) {
		return userDao.validateUser(user);
	}

}
