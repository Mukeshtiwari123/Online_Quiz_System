package com.demo.online_quiz_system.app;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.online_quiz_system.entities.Categories;
import com.demo.online_quiz_system.entities.Leaderboard;
import com.demo.online_quiz_system.entities.Quiz;
import com.demo.online_quiz_system.entities.User;
import com.demo.online_quiz_system.util.HibernateUtil;

public class QuizApp {
    public static void main(String[] args) {
        // Obtain a Hibernate SessionFactory
        SessionFactory factory = HibernateUtil.getSessionFactory();

        // Open a new session
        Session session = factory.getCurrentSession();
     // Open a new session (use openSession() instead of getCurrentSession())
        //Session session = factory.openSession();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();

        // Try to retrieve an existing category with ID 1
        Categories category = session.get(Categories.class, 1);

        // Check if category with ID 1 exists
        if (category == null) {
            System.out.println("No category with ID 1 found. Creating a new category.");

            // Create a new category since none exists
            category = new Categories();
            category.setName("Technology");
            category.setDescription("This is the Technology Category");

            // Save the new category
            session.save(category);
            System.out.println("New category created and saved to the database.");
        } else {
            System.out.println("Category with ID 1 found: " + category.getName());
        }

        // Retrieve or create a User with ID 1
        User user = session.get(User.class, 1);
        if (user == null) {
            System.out.println("No user with ID 1 found. Creating a new user.");
            
            // Create a new user
            user = new User();
            user.setUsername("john_doe");
            user.setPassword("password123");
            
            // Save the new user
            session.save(user);
            System.out.println("New user created and saved to the database.");
        } else {
            System.out.println("User with ID 1 found: " + user.getUsername());
        }

        // Create an empty set for leaderboard entries (as an example)
        Set<Leaderboard> leaderboardEntries = new HashSet<>();

        // Create a new Quiz object, no need to set quizId as it's auto-generated
        Quiz quiz = new Quiz("Java Basics Quiz", category, user, leaderboardEntries);

        // Save the Quiz to the database
        session.save(quiz);

        // Commit the transaction
        transaction.commit();

        // Close the session and factory
        session.close();
        factory.close();

        System.out.println("Quiz has been successfully saved with auto-generated ID.");
    }
}
