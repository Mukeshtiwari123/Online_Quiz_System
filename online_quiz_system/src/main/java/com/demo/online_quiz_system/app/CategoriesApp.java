package com.demo.online_quiz_system.app;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.online_quiz_system.entities.Categories;
import com.demo.online_quiz_system.entities.Quiz;
import com.demo.online_quiz_system.util.HibernateUtil;

public class CategoriesApp {
	public static void main(String [] args) {
		// Obtain a Hibernate SessionFactory
		SessionFactory factory = (SessionFactory) HibernateUtil.getSessionFactory();

		// Create a new Student
		//LocalDate date1 = LocalDate.of(1988, 1, 13);
		Set<Quiz> createdQuizzes = new HashSet<>();
        
		// Create a new Answer object
		Categories answers1 = new Categories("Hello", "This is the First Quiz", createdQuizzes);

		// Open a new session
		Session session = factory.getCurrentSession();  	
		// Begin a transaction
		Transaction transaction = session.beginTransaction();

		// Save the student to the database
		session.save(answers1);

		// Commit the transaction
		transaction.commit();

		// Close the Session
		session.close();

		// Close the Session Factory
		factory.close();
	}
}
