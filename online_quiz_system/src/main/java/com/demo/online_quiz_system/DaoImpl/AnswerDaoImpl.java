package com.demo.online_quiz_system.DaoImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.online_quiz_system.Dao.AnswerDao;
import com.demo.online_quiz_system.entities.*;
import com.demo.online_quiz_system.util.HibernateUtil;

import java.util.List;

public class AnswerDaoImpl implements AnswerDao {

    @Override
    public Answers createAnswer(Answers answer) {
    	Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
            session.save(answer);  // Save answer
            session.getTransaction().commit();
            return answer;
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Answers> getAllAnswers() {
    	Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
            return session.createQuery("from Answers", Answers.class).list();  // Fetch all answers
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Answers getAnswerById(int answerId) {
    	Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			return session.get(Answers.class, answerId);  // Fetch answer by ID
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Answers updateAnswer(int answerId, Answers updatedAnswer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start the transaction only once
            transaction = session.beginTransaction();
            
            // Fetch the existing answer from the database
            Answers existingAnswer = session.get(Answers.class, answerId);
            
            if (existingAnswer != null) {
                // Update the fields of the existing answer with the new values
                existingAnswer.setAnswer_text(updatedAnswer.getAnswer_text());
                existingAnswer.setIs_correct(updatedAnswer.getIs_correct());
                
                // Update the existing answer in the database
                session.update(existingAnswer);
                
                // Commit the transaction
                transaction.commit();
                
                return existingAnswer;  // Return the updated answer
            } else {
                System.out.println("Answer not found");
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback in case of an error
            }
            System.out.println("HibernateException: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Rollback in case of a general error
            }
            System.out.println("Exception: " + e.getMessage());
        }
        return null;  // Return null if the answer wasn't updated
    }


    @Override
    public String deleteAnswer(int answerId) {
    	Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
           // session.beginTransaction();
            Answers answer = session.get(Answers.class, answerId);
            if (answer != null) {
                session.delete(answer);  // Delete answer
                session.getTransaction().commit();
                return "Answer deleted successfully.";
            } else {
                return "Answer not found.";
            }
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Failed to delete the answer.";
    }
}
