package com.demo.online_quiz_system.DaoImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.online_quiz_system.Dao.QuestionDao;
import com.demo.online_quiz_system.entities.Question;
import com.demo.online_quiz_system.util.HibernateUtil;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

	@Override
	public Question createQuestion(Question question) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(question);  // Save question
			session.getTransaction().commit();
			return question;
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<Question> getAllQuestions() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			return session.createQuery("from Question", Question.class).list();  // Fetch all questions
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Question getQuestionById(int questionId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			return session.get(Question.class, questionId);  // Fetch question by ID
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Question updateQuestion(int questionId, Question updatedQuestion) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();  // Start the transaction

			// Check if the question exists
			Question existingQuestion = session.get(Question.class, questionId);
			if (existingQuestion == null) {
				System.out.println("Question with ID " + questionId + " not found.");
				return null;  // Handle the case where the question doesn't exist
			}

			// Update only the necessary fields
			existingQuestion.setQuestionText(updatedQuestion.getQuestionText());
			existingQuestion.setQuestionType(updatedQuestion.getQuestionType());
			existingQuestion.setQuestionText(updatedQuestion.getQuestionText());
			session.update(existingQuestion);  // Update the existing question
			transaction.commit();  // Commit the transaction
			return existingQuestion;
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();  // Rollback if there is an exception
			}
			e.printStackTrace();  // Print exception details
		} catch (Exception e) {
			e.printStackTrace();  // Print exception details
		}
		return null;
	}



	@Override
	public String deleteQuestion(int questionId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Question question = session.get(Question.class, questionId);
			if (question != null) {
				session.delete(question);  // Delete question
				session.getTransaction().commit();
				return "Question deleted successfully.";
			} else {
				return "Question not found.";
			}
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "Failed to delete the question.";
	}
}
