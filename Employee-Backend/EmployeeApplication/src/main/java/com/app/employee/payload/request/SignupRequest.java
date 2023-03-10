package com.app.employee.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*Request payload for the user to register on the portal*/
public class SignupRequest {
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String username;
	
	@NotBlank
	@Size(max=50)
	@Email
	private String email;
	
	private Set<String> roles;
	
	@NotBlank
	@Size(min = 8, max=40)
	private String password;

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

	public Set<String> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
