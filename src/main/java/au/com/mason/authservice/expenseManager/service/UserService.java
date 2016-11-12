package au.com.mason.expenseManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.mason.expenseManager.dao.UserDao;
import au.com.mason.expenseManager.domain.User;

@Component
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User validateUser(User user) {
		return userDao.validateUser(user);
	}

}
