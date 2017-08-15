package com.notes.entity;

import java.util.Date;

public class NoteDetailsTo extends NoteTo {

	private int id;
	private int userId;
	private Date createdTime;
	private Date updatedTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public NoteDetailsTo() {
	}
	public NoteDetailsTo(int id, int userId, Date createdTime, Date updatedTime) {
		this.id = id;
		this.userId = userId;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}
	
	
	public NoteDetailsTo(int id, int userId, String title, String note, Date createdTime, Date updatedTime) {
		super(title, note);
		this.id = id;
		this.userId = userId;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}
	
	
}
