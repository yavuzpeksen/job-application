package com.kodgemisi.assignment.domains.form;

import javax.validation.constraints.Size;


public class ResetPasswordForm {


	private String token;
	
	@Size(min=8, max=16)
	private String password;
	
	private String passwordConfirm;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
