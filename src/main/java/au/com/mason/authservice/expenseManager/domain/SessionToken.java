package au.com.mason.expenseManager.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
@Table(name = "sessionTokens")
public class SessionToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	private String token;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime expiryDateTime;
	
	

	public SessionToken(User user) {
		this.user = user;
		this.expiryDateTime = new LocalDateTime().plusMinutes(30);
		this.token = UUID.randomUUID().toString();
	}

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpiryDateTime() {
		return expiryDateTime;
	}

	public void setExpiryDateTime(LocalDateTime expiryDateTime) {
		this.expiryDateTime = expiryDateTime;
	}
	
}
