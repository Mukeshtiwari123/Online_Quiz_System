package com.demo.online_quiz_system.app;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.online_quiz_system.entities.Question;
import com.demo.online_quiz_system.entities.Quiz;
import com.demo.online_quiz_system.util.HibernateUtil;

public class QuestionApp {
    public static void main(String[] args) {
        // Obtain a Hibernate SessionFactory
        SessionFactory factory = HibernateUtil.getSessionFactory();

        // Create a new Quiz object (replace with an existing Quiz as needed)
        // Assume quizId 1 exists
        Quiz quiz = new Quiz(); // Adjust this based on how you retrieve an existing Quiz
        quiz.setQuizId(1); // Set the quizId for the existing Quiz

        // Create a new Question object using the constructor
        Question question = new Question();
        question.setQuiz(quiz);
        question.setQuestionText("What is the capital of France?");
        question.setQuestionType(Question.QuestionType.MULTIPLE_CHOICE);

        // Open a new session
        Session session = null;
        Transaction transaction = null;

        try {
            session = factory.openSession(); // Use openSession() for manual session management
            transaction = session.beginTransaction();

            // Save the question to the database
            session.save(question);

            // Commit the transaction
            transaction.commit();
            System.out.println("Question saved successfully: " + question);
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
