package com.notes.db.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique = true)
	private String email;
	private String password;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;

	public User() {

	}
	
	public User(int id) {
		this.id = id;
	}

	public User(int id, String email, String password, Date createdTime, Date updatedTime) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public User(String email, String password, Date createdTime, Date updatedTime) {
		this.email = email;
		this.password = password;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public User(Object username, Set<String> set) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}
