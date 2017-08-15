package com.notes.entity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserTo {

	public UserTo() {
	}
	
	public UserTo(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Size(min = 8)
	private String password;

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

	@Override
	public String toString() {
		return "UserTo [email=" + email + ", password=" + password + "]";
	}

	
}
