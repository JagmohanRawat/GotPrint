package com.notes.entity;

import java.util.Date;

public class UserDetailsTo extends UserTo {

	private int id;
	private Date createdTime;
	private Date updatedTime;

	public UserDetailsTo() {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserDetailsTo(Date createdTime, Date updatedTime) {
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public UserDetailsTo(int id, String email, String password, Date createdTime, Date updatedTime) {
		super(email, password);
		this.id = id;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

}
