package au.com.mason.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.mason.authservice.dao.UserApplicationDao;
import au.com.mason.authservice.dao.UserApplicationRoleDao;
import au.com.mason.authservice.dao.UserDao;
import au.com.mason.authservice.domain.User;
import au.com.mason.authservice.domain.UserApplication;
import au.com.mason.authservice.domain.UserApplicationRole;
import au.com.mason.authservice.dto.CreateUserInput;
import au.com.mason.authservice.dto.LoginInput;

@Component
public class UserApplicationService {
	
	@Autowired
	private UserApplicationDao userApplicationDao;
	
	@Autowired
	private UserApplicationRoleDao userApplicationRoleDao;	
	
	@Autowired
	private UserDao userDao;
	
	public UserApplication validateUser(LoginInput loginInput) {
		return userApplicationDao.validateUser(loginInput);
	}
	
	public boolean userNameExists(LoginInput loginInput) {
		return userApplicationDao.findByUserName(loginInput) != null;
	}
	
	public User createUser(CreateUserInput createUserInput) {
		User user = userDao.findByUserName(createUserInput.getUserName());
		
		if (user == null) {
			User newUser = new User();
			newUser.setUserName(createUserInput.getUserName());
			newUser.setPassword(createUserInput.getPassword());
			
			userDao.create(newUser);
			user = newUser;
		}
		
		UserApplication userApplication = new UserApplication();
		userApplication.setUser(user);
		userApplication.setApplicationType(createUserInput.getApplicationType());
		userApplicationDao.create(userApplication);
		
		createUserInput.getRoles().forEach(role -> {
			UserApplicationRole userApplicationRole = new UserApplicationRole();
			userApplicationRole.setUserApplication(userApplication);
			userApplicationRole.setRole(role);
			userApplicationRoleDao.create(userApplicationRole);
			userApplication.addUserApplicationRole(userApplicationRole);
		});
		
		userDao.update(user);
		
		return user;
	}

}
