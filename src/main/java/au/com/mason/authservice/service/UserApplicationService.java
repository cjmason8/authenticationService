package au.com.mason.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.mason.authservice.dao.UserApplicationDao;
import au.com.mason.authservice.domain.UserApplication;
import au.com.mason.authservice.dto.LoginInput;

@Component
public class UserApplicationService {
	
	@Autowired
	private UserApplicationDao userApplicationDao;
	
	@Autowired
	private UserDao userDao;
	
	public UserApplication validateUser(LoginInput loginInput) {
		return userApplicationDao.validateUser(loginInput);
	}
	
	public boolean userNameExists(LoginInput loginInput) {
		return userApplicationDao.findByUserName(loginInput) != null;
	}

}
