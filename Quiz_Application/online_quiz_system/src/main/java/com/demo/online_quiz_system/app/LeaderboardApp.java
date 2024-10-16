package com.demo.online_quiz_system.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.online_quiz_system.entities.Categories;
import com.demo.online_quiz_system.entities.Leaderboard;
import com.demo.online_quiz_system.entities.Quiz;
import com.demo.online_quiz_system.entities.User;
import com.demo.online_quiz_system.util.HibernateUtil;

public class LeaderboardApp {

    public static void main(String[] args) {
        // Obtain a Hibernate SessionFactory
        SessionFactory factory = HibernateUtil.getSessionFactory();

        // Open a new session
        Session session = factory.openSession();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();

        // Retrieve or create a Category for the quiz
        Categories category = session.get(Categories.class, 1);
        if (category == null) {
            System.out.println("No category with ID 1 found. Creating a new category.");
            category = new Categories();
            category.setName("Technology");
            category.setDescription("This is the Technology Category");
            session.save(category);
        }

        // Retrieve or create a User for the leaderboard
        User user = session.get(User.class, 1);
        if (user == null) {
            System.out.println("No user with ID 1 found. Creating a new user.");
            user = new User();
            user.setUsername("john_doe");
            user.setPassword("password123");
            session.save(user);
        }

        // Retrieve or create a Quiz for the leaderboard
        Quiz quiz = session.get(Quiz.class, 1);
        if (quiz == null) {
            System.out.println("No quiz with ID 1 found. Creating a new quiz.");
            quiz = new Quiz("Java Basics Quiz", category, user, null);
            session.save(quiz);
        }

        // Create a new Leaderboard entry
        Leaderboard leaderboardEntry = new Leaderboard();
        leaderboardEntry.setQuiz(quiz);
        leaderboardEntry.setUser(user);
        leaderboardEntry.setScore(85.5);
        leaderboardEntry.setRanking(1);

        // Save the leaderboard entry
        session.save(leaderboardEntry);

        // Commit the transaction
        transaction.commit();

        // Close the session and factory
        session.close();
        factory.close();

        System.out.println("Leaderboard entry has been successfully saved.");
    }
}
