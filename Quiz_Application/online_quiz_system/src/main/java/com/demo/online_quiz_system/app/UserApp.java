package com.demo.online_quiz_system.app;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.online_quiz_system.entities.Answers;
import com.demo.online_quiz_system.entities.Leaderboard;
import com.demo.online_quiz_system.entities.Quiz;
import com.demo.online_quiz_system.entities.Result;
import com.demo.online_quiz_system.entities.User;
import com.demo.online_quiz_system.util.HibernateUtil;

public class UserApp {
    public static void main(String[] args) {
        // Obtain a Hibernate SessionFactory (no cast needed)
        SessionFactory factory = HibernateUtil.getSessionFactory();

        // Create a new Answer object
        Set<Quiz> createdQuizzes = new HashSet<>();
        Set<Result> results = new HashSet<>();
        Set<Leaderboard> leaderboardEntries = new HashSet<>();

        // Creating a new User object using the constructor
        User user1 = new User("Shreyash", "shr123", createdQuizzes, results, leaderboardEntries);

        // Open a new session
        Session session = null;
        Transaction transaction = null;

        try {
            session = factory.openSession(); // Use openSession() instead of getCurrentSession()
            transaction = session.beginTransaction();

            // Save the user to the database
            session.save(user1);

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Roll back if there is an exception
            e.printStackTrace();
        } finally {
            if (session != null) session.close(); // Close the session
            // Do not close the factory here if you plan to use it again later
            // factory.close(); // Uncomment this if you are done with all operations
        }
    }
}
