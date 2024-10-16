package com.demo.online_quiz_system.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.online_quiz_system.entities.Answers;
import com.demo.online_quiz_system.util.HibernateUtil;

public class AnswerApp {
	public static void main(String []args) {
		// Obtain a Hibernate SessionFactory
		SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

		// Create a new Student
		//LocalDate date1 = LocalDate.of(1988, 1, 13);

		// Create a new Answer object
		Answers answers1 = new Answers("2", "This is the 6th Answer", "true");

		// Open a new session
		Session session = sessionFactory.openSession();  	
		// Begin a transaction
		Transaction transaction = session.beginTransaction();

		// Save the student to the database
		session.save(answers1);

		// Commit the transaction
		transaction.commit();

		// Close the Session
		session.close();

		// Close the Session Factory
		sessionFactory.close();
	}
}

