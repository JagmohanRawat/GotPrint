package com.notes.db.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String note;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	public Note() {
	}

	public Note(String title, String note, Date createdTime, Date updatedTime) {
		this.title = title;
		this.note = note;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public Note(int id, String title, String note, Date createdTime, Date updatedTime) {
		this.id = id;
		this.title = title;
		this.note = note;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Note(int id, String title, String note, Date createdTime, Date updatedTime, User user) {
		this.id = id;
		this.title = title;
		this.note = note;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.user = user;
	}

}
