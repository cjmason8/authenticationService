package au.com.mason.authservice.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	private Timestamp expiryDateTime;
	
	private SessionToken() {}

	public SessionToken(User user) {
		this.user = user;
		this.expiryDateTime = Timestamp.valueOf(LocalDateTime.now().plusMinutes(120));
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
		return expiryDateTime.toLocalDateTime();
	}

	public void setExpiryDateTime(LocalDateTime expiryDateTime) {
		this.expiryDateTime = Timestamp.valueOf(expiryDateTime);
	}
	
}
