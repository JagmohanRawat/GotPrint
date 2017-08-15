package com.notes.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.notes.db.entity.Note;
import com.notes.db.entity.User;
import com.notes.entity.NoteDetailsTo;
import com.notes.entity.NoteTo;

public class NoteRepository {

	public int create(NoteTo noteTo, int userId) {
		Note note = new Note(noteTo.getTitle(), noteTo.getNote(), new Date(), new Date());
		note.setUser(new User(userId));
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Serializable id = session.save(note);
		session.getTransaction().commit();
		session.close();
		return (int) id;
	}

	public int update(NoteTo noteTo, int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("UPDATE " + Note.class.getName()
				+ " SET title=:title, note=:note, updatedTime=:updatedTime WHERE id=:id");
		query.setParameter("id", id).setParameter("title", noteTo.getTitle()).setParameter("note", noteTo.getNote())
				.setParameter("updatedTime", new Date());
		int updatedRows = query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		return updatedRows;
	}

	public Note delete(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Note note = (Note) session.get(Note.class, id);
		if (note != null) {
			session.delete(note);
		}
		session.getTransaction().commit();
		session.close();
		return note;
	}

	public NoteDetailsTo find(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Note note = (Note) session.get(Note.class, id);
		if (note == null) {
			return null;
		}
		return new NoteDetailsTo(id, note.getUser().getId(), note.getTitle(), note.getNote(), note.getCreatedTime(),
				note.getUpdatedTime());
	}

	public List<NoteDetailsTo> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Note> notes = (List<Note>) session.createQuery("FROM " + Note.class.getName()).list();
		if (notes == null) {
			return null;
		}
		List<NoteDetailsTo> noteDetails = new ArrayList<>();
		for (Note note : notes) {
			noteDetails.add(new NoteDetailsTo(note.getId(), note.getUser().getId(), note.getTitle(), note.getNote(),
					note.getCreatedTime(), note.getUpdatedTime()));
		}
		return noteDetails;
	}

	public List<NoteDetailsTo> findAllByUserId(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Note> notes = (List<Note>) session.createQuery("FROM " + Note.class.getName() + " WHERE userId=:userId")
				.setParameter("userId", id).list();
		if (notes == null) {
			return null;
		}
		List<NoteDetailsTo> noteDetails = new ArrayList<>();
		for (Note note : notes) {
			noteDetails.add(new NoteDetailsTo(note.getId(), note.getUser().getId(), note.getTitle(), note.getNote(),
					note.getCreatedTime(), note.getUpdatedTime()));
		}
		return noteDetails;
	}
	
	public List<Note> deleteAllByUserId(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Note> notes = (List<Note>) session.createQuery("FROM " + Note.class.getName() + " WHERE userId=:userId")
				.setParameter("userId", id).list();
		if (notes == null) {
			return null;
		}
		for (Note note : notes) {
			session.delete(note);
		}
		session.getTransaction().commit();		
		return notes;
	}
}
