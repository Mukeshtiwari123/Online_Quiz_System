package com.demo.online_quiz_system.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.demo.online_quiz_system.entities.Quiz;
import com.demo.online_quiz_system.entities.Result;
import com.demo.online_quiz_system.entities.User;

public class ResultApp {

    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory factory = new Configuration()
                .configure("Hibernate.cfg.xml")
                .addAnnotatedClass(Result.class)
                .addAnnotatedClass(Quiz.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();

        // Create a session
        Session session = factory.getCurrentSession();

        try {
            // Start a transaction
            session.beginTransaction();

            // Retrieve a quiz and user from the database (make sure they exist)
            int quizId = 1;
            int userId = 1;
            Quiz quiz = session.get(Quiz.class, quizId);
            User user = session.get(User.class, userId);

            if (quiz != null && user != null) {
                // Create a new Result object
                Result newResult = new Result(quiz, user, 95.0);

                // Save the result
                session.save(newResult);
                
                System.out.println("Result saved: " + newResult);
            } else {
                System.out.println("Quiz or User not found.");
            }

            // Commit the transaction
            session.getTransaction().commit();
        } finally {
        	 session.close();  // Ensure session is closed
            // Close the factory
            factory.close();
        }
    }
}
