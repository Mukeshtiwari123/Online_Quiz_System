package com.demo.online_quiz_system.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.demo.online_quiz_system.Dao.AnswerDao;
import com.demo.online_quiz_system.entities.Answers;
import com.demo.online_quiz_system.util.HibernateUtil;

public class AnswerDaoImpl implements AnswerDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Answers saveAnswer(Answers answer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(answer);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public Answers getAnswerById(int id) {
        Answers answer = null;
        try (Session session = sessionFactory.openSession()) {
            answer = session.get(Answers.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public List<Answers> getAllAnswers() {
        List<Answers> answers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Query<Answers> query = session.createQuery("FROM Answers", Answers.class);
            answers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
        }
        return answers;
    }


    @Override
    public List<Answers> getAnswersByCategoryId(int categoryId) {
        List<Answers> answersList = null;
        try (Session session = sessionFactory.openSession()) {
            Query<Answers> query = session.createQuery("FROM Answers WHERE category_id = :categoryId", Answers.class);
            query.setParameter("categoryId", categoryId);
            answersList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answersList;
    }

    @Override
    public void updateAnswer(Answers answer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(answer);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAnswer(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Answers answer = session.get(Answers.class, id);
            if (answer != null) {
                session.delete(answer);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
