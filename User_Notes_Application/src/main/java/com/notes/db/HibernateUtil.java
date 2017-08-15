package com.notes.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	private HibernateUtil() {
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			init();
		}
		return sessionFactory;
	}

	private static void init() {
		AnnotationConfiguration configuration = new AnnotationConfiguration().configure("hibernate.cfg.xml");
		sessionFactory = configuration.buildSessionFactory();
	}
}
