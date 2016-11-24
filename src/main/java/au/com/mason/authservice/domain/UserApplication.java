package au.com.mason.authservice.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "userapplications")
public class UserApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	@Enumerated(EnumType.STRING)
	private ApplicationType applicationType;
	
	@OneToMany(mappedBy = "userApplication")
	private List<UserApplicationRole> userApplicationRoles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ApplicationType getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(ApplicationType applicationType) {
		this.applicationType = applicationType;
	}

	public List<Role> getRoles() {
		List<Role> roles = new ArrayList<>();
		for (UserApplicationRole userApplicationRole : userApplicationRoles) {
			roles.add(userApplicationRole.getRole());
		}
		
		return roles;
	}

	public void setUserApplicationRoles(List<UserApplicationRole> userApplicationRoles) {
		this.userApplicationRoles = userApplicationRoles;
	}

}
