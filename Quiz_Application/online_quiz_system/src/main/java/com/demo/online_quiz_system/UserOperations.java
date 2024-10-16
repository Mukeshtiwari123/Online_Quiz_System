//package com.demo.online_quiz_system;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
//import com.demo.online_quiz_system.entities.User;
//import com.demo.online_quiz_system.HibernateUtil;
//
//public class UserOperations {
//    public static void main(String[] args) {
//        // Obtain a Hibernate SessionFactory
//        SessionFactory factory = HibernateUtil.getSessionFactory();
//        // Create new User objects
//        User user1 = new User();
//        user1.setUsername("johndoe");
//        user1.setPassword("password123");
//
//        User user2 = new User();
//        user2.setUsername("janesmith");
//        user2.setPassword("password456");
//
//        // Open a new session
//        Session session = factory.openSession();
//
//        // Begin a transaction
//        Transaction transaction = session.beginTransaction();
//
//        // Save the user objects to the database
//        session.save(user1);
//        session.save(user2);
//
//        // Commit the transaction
//        transaction.commit();
//
//        // Close the session
//        session.close();
//
//        // Close the Session Factory
//        factory.close();
//    }
//}
