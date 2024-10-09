package com.demo.online_quiz_system.DaoImpl;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.online_quiz_system.Dao.CategoriesDao;
import com.demo.online_quiz_system.entities.Categories;
import com.demo.online_quiz_system.util.HibernateUtil;
import java.util.List;

import javax.persistence.Query;

public class CategoriesDaoImpl implements CategoriesDao {


	@Override
	public Categories createCategory(Categories category) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();  // Start the transaction
	        session.save(category);  // Save the category object
	        transaction.commit();  // Commit the transaction
	        System.out.println("Category saved successfully.");
	        return category;  // Return the saved category
	    } catch (HibernateException e) {
	        if (transaction != null) {
	            transaction.rollback();  // Rollback in case of failure
	        }
	        System.out.println("Error while saving category: " + e.getMessage());
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();  // Rollback in case of any other exceptions
	        }
	        System.out.println("Error: " + e.getMessage());
	    }
	    return null;  // Return null in case of failure
	}


	@Override
	public List<Categories> getAllCategories() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			return session.createQuery("from Categories", Categories.class).list();  // Fetch all categories
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Categories getCategoryById(int categoryId) {
		Transaction transaction = null;
		Categories category = null; // Declare category outside of try block for returning it later

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			// Fetch the category by ID
			category = session.get(Categories.class, categoryId);

			if (category != null) {
				// Manually initialize the quizzes collection (to avoid LazyInitializationException)
				Hibernate.initialize(category.getQuizzes());
			} else {
				System.out.println("Category not found with ID: " + categoryId);
			}

			transaction.commit(); // Commit the transaction if everything goes well

		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback(); // Rollback in case of an error
			}
			System.out.println("Hibernate Exception: " + e.getMessage());
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Exception: " + e.getMessage());
		}

		// Return the fetched category or null if it doesn't exist
		return category;
	}


	@Override
	public Categories updateCategory(int categoryId, Categories updatedCategory) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			// Fetch the category from the database
			Categories existingCategory = session.get(Categories.class, categoryId);

			System.out.println("Existing Category: " + existingCategory); // Debugging output

			if (existingCategory != null) {
				// Update the fields of the existing category
				existingCategory.setName(updatedCategory.getName());
				existingCategory.setDescription(updatedCategory.getDescription());

				session.update(existingCategory);
				transaction.commit();

				System.out.println("Updated Category: " + existingCategory); // Debugging output
				return existingCategory;
			} else {
				System.out.println("Category not found with ID: " + categoryId); // Debugging output
				return null;
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public String deleteCategory(int categoryId) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction=session.beginTransaction();
			Categories category = session.get(Categories.class, categoryId);
			if (category != null) {
				session.delete(category);  // Delete category
				session.getTransaction().commit();
				return "Category deleted successfully.";
			} else {
				return "Category not found.";
			}
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "Failed to delete the category.";
	}

	@Override

	public boolean isCategoryNameExists(String categoryName) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Categories c WHERE c.name = :categoryName";
			Query query = session.createQuery(hql); // Remove generic type parameter
			query.setParameter("categoryName", categoryName);
			return !((org.hibernate.query.Query<Categories>) query).list().isEmpty(); // Return true if any result is found
		} catch (Exception e) {
			System.out.println("Error checking category name existence: " + e.getMessage());
			return false; // In case of an error, return false
		}
	}
}
