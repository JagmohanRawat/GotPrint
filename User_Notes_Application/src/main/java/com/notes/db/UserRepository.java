package com.notes.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.notes.db.entity.User;
import com.notes.entity.UserDetailsTo;
import com.notes.entity.UserTo;

public class UserRepository {

	public int create(UserTo userTo) {
		User user = new User(userTo.getEmail(), userTo.getPassword(), new Date(), new Date());
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Serializable id = session.save(user);
		session.getTransaction().commit();
		session.close();
		return (int) id;
	}

	public int update(UserTo userTo, int id) {
		System.out.println(userTo);
		System.out.println(id);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("UPDATE "+User.class.getName()+" SET email=:email, password=:password, updatedTime=:updatedTime WHERE id=:id");
		query.setParameter("id", id).setParameter("password", userTo.getPassword()).setParameter("email", userTo.getEmail())
		.setParameter("updatedTime", new Date());
		int updatedRows = query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		return updatedRows;
	}

	public User delete(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		User user = (User)session.get(User.class, id);
		if (user != null) {
			session.delete(user);
		}
		session.getTransaction().commit();
		session.close();
		return user;
	}

	public UserDetailsTo find(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		User user = (User)session.get(User.class, id);
		if (user == null) {
			return null;
		}
		return new UserDetailsTo(id, user.getEmail(), user.getPassword(), user.getCreatedTime(), user.getUpdatedTime());
	}

	public List<UserDetailsTo> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>)session.createQuery("FROM "+User.class.getName()).list();
		if (users == null) {
			return null;
		}
		List<UserDetailsTo> userDetails = new ArrayList<>();
		for (User user : users) {
			userDetails.add(new UserDetailsTo(user.getId(), user.getEmail(), user.getPassword(), user.getCreatedTime(),
					user.getUpdatedTime()));
		}
		return userDetails;
	}
}
