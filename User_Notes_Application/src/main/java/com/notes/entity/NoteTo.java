package com.notes.entity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteTo {

	public NoteTo() {
	}
	
	public NoteTo(String title, String note) {
		this.title = title;
		this.note = note;
	}

	@NotEmpty
	@Size(max = 50)
	private String title;
	@Size(max = 1000)
	private String note;

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

}
