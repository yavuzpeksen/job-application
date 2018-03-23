package com.kodgemisi.assignment.domains;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class PasswordResetToken {

	private static final int EXPIRATION = 60 * 24;

	private Long id;
	
	private String token;
	
	private Test user;
	
	@Column(name="expiration_date")
	private Date expirationDate;
	
	public PasswordResetToken() {
		expirationDate = calculateExpirationDate(EXPIRATION);
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}

	@OneToOne
	@JoinColumn(name="user_id")
	public Test getUser() {
		return user;
	}

	public void setUser(Test user) {
		this.user = user;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


	private Date calculateExpirationDate(final int expirationTimeInMinutes) {
    final Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(new Date().getTime());
    cal.add(Calendar.MINUTE, expirationTimeInMinutes);
    return new Date(cal.getTime().getTime());
	}
	
}
