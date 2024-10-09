package com.demo.online_quiz_system.DaoImpl;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.online_quiz_system.Dao.QuizDao;
import com.demo.online_quiz_system.entities.Leaderboard;
import com.demo.online_quiz_system.entities.Question;
import com.demo.online_quiz_system.entities.Quiz;
import com.demo.online_quiz_system.entities.Result;
import com.demo.online_quiz_system.util.HibernateUtil;

import java.util.List;

public class QuizDaoImpl implements QuizDao {

	@Override
	public Quiz createQuiz(Quiz quiz) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(quiz);  // Save quiz
			transaction.commit();  // Commit the transaction
			return quiz;
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
		return null;  // Return null if the quiz wasn't created
	}


	@Override
	public List<Quiz> getAllQuizzes() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			List<Quiz> quizzes = session.createQuery("from Quiz", Quiz.class).list();  // Fetch all quizzes
			transaction.commit();  // Commit the transaction
			return quizzes;
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
		return null;  // Return null if no quizzes are found
	}

	@Override
	public Quiz getQuizById(int quizId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			Quiz quiz = session.get(Quiz.class, quizId); // Fetch quiz by ID

			if (quiz != null) {
				Hibernate.initialize(quiz.getLeaderboardEntries()); // Initialize the collection
			}

			return quiz; // Return the quiz object with initialized collection
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}


	@Override
	public Quiz updateQuiz(int quizId, Quiz updatedQuiz) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			// Fetch the existing quiz from the database
			Quiz existingQuiz = session.get(Quiz.class, quizId);

			if (existingQuiz != null) {
				// Update the fields of the existing quiz with the new values
				existingQuiz.setTitle(updatedQuiz.getTitle());
				//existingQuiz.setDescription(updatedQuiz.getDescription());
				//existingQuiz.setTotalQuestions(updatedQuiz.getTotalQuestions());
				// You can add more fields to update as needed

				// Update the existing quiz in the database
				session.update(existingQuiz);

				transaction.commit();  // Commit the transaction
				return existingQuiz;  // Return the updated quiz
			} else {
				System.out.println("Quiz not found");
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
		return null;  // Return null if the quiz wasn't updated
	}

	@Override
	public String deleteQuiz(int quizId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			Quiz quiz = session.get(Quiz.class, quizId);

			if (quiz != null) {
				// Step 1: Clear associations from Quiz
				// Remove from Category
				if (quiz.getCategory() != null) {
					quiz.getCategory().getQuizzes().remove(quiz); // Break association on Category side
					quiz.setCategory(null);
				}

				// Remove from User
				if (quiz.getCreatedBy() != null) {
					quiz.getCreatedBy().getCreatedQuizzes().remove(quiz); // Break association on User side
					quiz.setCreatedBy(null);
				}

				// Step 2: Remove all associated Results
				if (quiz.getResults() != null) {
					for (Result result : quiz.getResults()) {
						result.setQuiz(null); // Break the association
						session.update(result); // Explicitly update the result
					}
					quiz.getResults().clear(); // Clear the list to remove references
				}

				// Step 3: Remove all associated Leaderboard entries
				if (quiz.getLeaderboardEntries() != null) {
					for (Leaderboard leaderboard : quiz.getLeaderboardEntries()) {
						leaderboard.setQuiz(null); // Break the association
						session.update(leaderboard); // Explicitly update the leaderboard entry
					}
					quiz.getLeaderboardEntries().clear(); // Clear the set to remove references
				}

				// Step 4: Now delete the quiz
				session.delete(quiz); // Finally, delete the quiz
				transaction.commit(); // Commit the transaction

				return "Quiz deleted successfully.";
			} else {
				return "Quiz not found.";
			}
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback(); // Rollback the transaction in case of error
			}
			e.printStackTrace(); // Print stack trace for debugging
			return "Failed to delete the quiz.";
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // Rollback the transaction in case of error
			}
			e.printStackTrace(); // Print stack trace for debugging
			return "Failed to delete the quiz due to an unexpected error.";
		}
	}
}
