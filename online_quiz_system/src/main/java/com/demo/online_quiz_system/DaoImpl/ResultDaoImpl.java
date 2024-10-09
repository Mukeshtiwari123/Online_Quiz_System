package com.demo.online_quiz_system.DaoImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.online_quiz_system.Dao.ResultDao;
import com.demo.online_quiz_system.entities.Result;
import com.demo.online_quiz_system.util.HibernateUtil;
import java.util.List;

public class ResultDaoImpl implements ResultDao {

	@Override
	public Result createResult(Result result) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(result);  // Save result object
			session.getTransaction().commit();
			return result;
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<Result> getAllResults() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			return session.createQuery("from Result", Result.class).list();  // Fetch all results
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Result getResultById(int resultId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			return session.get(Result.class, resultId);  // Fetch result by ID
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Result updateResult(int resultId, Result updatedResult) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();  // Start transaction

			// Check if the result exists in the database
			Result existingResult = session.get(Result.class, resultId);
			if (existingResult == null) {
				System.out.println("Result with ID " + resultId + " not found.");
				return null;  // Return if result does not exist
			}

			// Update the fields
			existingResult.setQuiz(updatedResult.getQuiz());
			existingResult.setScore(updatedResult.getScore());
			existingResult.setTakenAt(updatedResult.getTakenAt());
			existingResult.setUser(updatedResult.getUser());

			session.update(existingResult);  // Update the existing result
			transaction.commit();  // Commit the transaction
			return existingResult;
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();  // Rollback if there is an exception
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String deleteResult(int resultId) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        // Load the result entity
	        Result result = session.get(Result.class, resultId);
	        if (result != null) {
	            // Remove result from associations
	            if (result.getQuiz() != null) {
	                result.getQuiz().removeResult(result);
	                result.setQuiz(null); // Prevent re-saving
	            }
	            if (result.getUser() != null) {
	                result.getUser().getResults().remove(result);
	                result.setUser(null); // Prevent re-saving
	            }

	            // Now delete the result
	            session.delete(result);
	            transaction.commit();
	            return "Result deleted successfully";
	        } else {
	            return "Result with ID: " + resultId + " not found";
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        return "Error while deleting Result";
	    }
	}




}
