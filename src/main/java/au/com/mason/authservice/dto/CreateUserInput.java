package au.com.mason.authservice.dto;

import java.util.List;

import au.com.mason.authservice.domain.ApplicationType;
import au.com.mason.authservice.domain.Role;

public class CreateUserInput {

	private String userName;
	private String password;
	private ApplicationType applicationType;
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ApplicationType getApplicationType() {
		return applicationType;
	}
	
	public void setApplicationType(ApplicationType applicationType) {
		this.applicationType = applicationType;
	}
	
}
