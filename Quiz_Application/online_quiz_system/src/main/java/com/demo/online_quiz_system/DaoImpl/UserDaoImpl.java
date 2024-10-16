package com.demo.online_quiz_system.DaoImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.online_quiz_system.*;
import com.demo.online_quiz_system.Dao.UserDao;
import com.demo.online_quiz_system.entities.Categories;
import com.demo.online_quiz_system.entities.Leaderboard;
import com.demo.online_quiz_system.entities.Quiz;
import com.demo.online_quiz_system.entities.Result;
import com.demo.online_quiz_system.entities.User;
import com.demo.online_quiz_system.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	private Session session; // Inject the Hibernate Session
	public UserDaoImpl() {
		this.session = HibernateUtil.getSessionFactory().openSession(); // Ensure the session is opened properly
	}
	Scanner sc = new Scanner(System.in);

	@Override
	public User createUser(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			return user;
		} catch (HibernateException e) {
			System.out.println("HibernateException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<User> getAllStudents() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			// Execute HQL query to retrieve all users
			Query<User> query = session.createQuery("FROM User", User.class);
			List<User> userList = query.list();
			return userList;
		} catch (HibernateException e) {
			System.out.println("HibernateException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return null;
	}

	@Override
	public User getUser(String userID) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			// Retrieve the user by ID
			User user = session.get(User.class, Integer.parseInt(userID));
			return user;
		} catch (HibernateException e) {
			System.out.println("HibernateException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return null;
	}

	@Override
	public User updateUser(String userID, User updatedUser) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			// Retrieve the existing user by ID
			User user = session.get(User.class, Integer.parseInt(userID));
			//session.beginTransaction();

			// Update user fields
			if (user != null) {
				user.setUsername(updatedUser.getUsername());
				user.setPassword(updatedUser.getPassword());
				// Add other fields as necessary

				session.saveOrUpdate(user);
				session.getTransaction().commit();
			}
			return user;
		} catch (HibernateException e) {
			System.out.println("HibernateException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return null;
	}

	@Override
	public void deleteUser(int userId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			// Fetch the User by ID
			User user = session.get(User.class, userId);

			if (user != null) {
				// Step 1: Remove associated quizzes
				if (user.getCreatedQuizzes() != null && !user.getCreatedQuizzes().isEmpty()) {
					for (Quiz quiz : user.getCreatedQuizzes()) {
						// Remove quiz from its Category
						if (quiz.getCategory() != null) {
							Categories category = quiz.getCategory();
							category.getQuizzes().remove(quiz); // Remove quiz from category's quizzes
							quiz.setCategory(null); // Break association
							session.update(category); // Update category in session
						}

						// Remove associations with Results
						if (quiz.getResults() != null && !quiz.getResults().isEmpty()) {
							for (Result result : quiz.getResults()) {
								result.setQuiz(null); // Break association with quiz
								session.update(result); // Update result in session
							}
							quiz.getResults().clear(); // Clear results list
						}

						// Remove associations with Leaderboard entries
						if (quiz.getLeaderboardEntries() != null && !quiz.getLeaderboardEntries().isEmpty()) {
							for (Leaderboard leaderboard : quiz.getLeaderboardEntries()) {
								leaderboard.setQuiz(null); // Break association with quiz
								session.update(leaderboard); // Update leaderboard in session
							}
							quiz.getLeaderboardEntries().clear(); // Clear leaderboard entries
						}

						// Finally, remove the quiz
						session.remove(quiz);
					}
					user.getCreatedQuizzes().clear(); // Clear the set of quizzes from the user
				}

				// Step 2: Remove associated results
				if (user.getResults() != null && !user.getResults().isEmpty()) {
					for (Result result : user.getResults()) {
						session.remove(result);
					}
					user.getResults().clear();
				}

				// Step 3: Remove associated leaderboard entries
				if (user.getLeaderboardEntries() != null && !user.getLeaderboardEntries().isEmpty()) {
					for (Leaderboard leaderboard : user.getLeaderboardEntries()) {
						session.remove(leaderboard);
					}
					user.getLeaderboardEntries().clear();
				}

				// Step 4: Finally, delete the user
				session.remove(user);

				// Commit the transaction
				transaction.commit();
			} else {
				System.out.println("User with ID " + userId + " not found.");
			}
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}


	@Override
	public boolean isUsernameExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}


