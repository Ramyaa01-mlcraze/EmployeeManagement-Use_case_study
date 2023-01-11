package com.app.employee.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/*Collection created to store the users of the portal*/
@Document(collection = "users")
public class User {
	
	
	
	@Id
	private String id;
	
	
	private String username;
	
	
	private String email;
	
	
	private String password;
	
	private boolean accountLocked;
    
    private int failedAttempt;
     
    private Date lockTime;
	
	/* Referring the DB to access the roles of the users */
	@DBRef
	private Set<Role> roles = new HashSet<>();

	public User() {
	
	}

	public User(String username,String email,String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	//getters and setters of the method
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getRoles(){
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public int getFailedAttempt() {
		return failedAttempt;
	}

	public void setFailedAttempt(int failedAttempt) {
		this.failedAttempt = failedAttempt;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	
	

	
	
	
	
	
	
	
	
}
