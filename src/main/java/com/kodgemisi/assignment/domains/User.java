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
	
	private JobListing jobListing;

	private String passwordConfirm;

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
  
  //MappedBy yapiyoruz cunku bu field User class'inda sorumlu degil, JobListing class'indaki "user" field'ina bak diyoruz.
  @OneToOne(cascade = CascadeType.ALL, mappedBy="user")
  public JobListing getJobListing(){
  	return jobListing;
  }
  
	public void setJobListing(JobListing jobListing) {
		this.jobListing = jobListing;
	}

  public void setRoles(Set<Role> roles) {
      this.roles = roles;
  }
}
