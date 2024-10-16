package com.demo.online_quiz_system.DaoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.demo.online_quiz_system.Dao.QuestionDao;
import com.demo.online_quiz_system.entities.Question;
import com.demo.online_quiz_system.util.HibernateUtil;
import java.util.List;

import javax.persistence.Query;

public class QuestionDaoImpl implements QuestionDao {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Override
	public Question saveQuestion(Question question) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.save(question);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}

	@Override
	public Question getQuestionById(int id) {
		Question question = null;
		try (Session session = sessionFactory.openSession()) {
			question = session.get(Question.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}

	@Override
	public List<Question> getAllQuestions() {
		List<Question> questions = null;
		try (Session session = sessionFactory.openSession()) {
			Query query = session.createQuery("FROM Question", Question.class);
			questions = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}

	@Override
	public void updateQuestion(Question question) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.update(question);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteQuestion(int id) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			Question question = session.get(Question.class, id);
			if (question != null) {
				session.delete(question);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
