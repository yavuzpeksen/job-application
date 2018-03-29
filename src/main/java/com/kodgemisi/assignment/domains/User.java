package com.kodgemisi.assignment.domains;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;

@Entity(name="USER")
@Table(name="USER")
public class User {
	
	private Long id;
	
	private String email;
	
	private String password;
	
	private Timestamp creationDate;
	
	private Set<Role> roles;
	
	private boolean authenticated;
	
	private String passwordConfirm;
	
	private PasswordResetToken passwordResetToken;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
  public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getPassword(){
		return password;
	}
	public void setPassword(String password) {
		this.password = password;		
	}
	
	public Timestamp getCreationDate(){
		return creationDate;
	}
	
	public void setCreationDate(Timestamp creationDate){
		this.creationDate = creationDate;
	}

	@Transient
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
  @ManyToMany
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  public Set<Role> getRoles() {
      return roles;
  }
  
  public void setRoles(Set<Role> roles) {
      this.roles = roles;
  }

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	@OneToOne(mappedBy="user")
	public PasswordResetToken getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(PasswordResetToken passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}
}
