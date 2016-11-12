package au.com.mason.authservice.dto;

import au.com.mason.authservice.domain.ApplicationType;

public class LoginInput {

	private String userName;
	private String password;
	private ApplicationType applicationType;

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
