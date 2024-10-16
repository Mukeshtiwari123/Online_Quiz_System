//package com.demo.online_quiz_system;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import com.demo.online_quiz_system.util.HibernateUtil;
//import com.demo.online_quiz_system.entities.Address;
//import com.demo.online_quiz_system.entities.Student;
//public class App 
//{
//	public static void main(String[] args) 
//	{
//		// Obtain a Hibernate SessionFactory
//		SessionFactory factory = HibernateUtil.getSessionFactory();
//		// Open a new session
//		Session session = factory.openSession();
//		// Begin a transaction
//		Transaction transaction = session.beginTransaction();
//		//create Address object
//		Address address1 = new Address(1,"c.k road", "london", "UK", "E1 7HT");
//		//create Employee Object
//		Student emp1 = new Student("E101", "lilly", 90000.0, address1);
//		//Save the student to the database
//		session.save(emp1);
//		//Save the address to the database
//		session.save(address1);
//		//Commit the transaction
//		transaction.commit();
//		//retrieve the data
//		Student employee = session.get(Student.class, "E101");
//		//display the data
//		System.out.println(employee);
//		//Close the Session
//		session.close();
//		//Close the Session Factory
//		factory.close();
//
//	}
//}