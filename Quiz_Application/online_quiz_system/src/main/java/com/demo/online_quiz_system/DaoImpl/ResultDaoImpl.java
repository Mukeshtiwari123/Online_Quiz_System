package com.demo.online_quiz_system.DaoImpl;



import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.demo.online_quiz_system.Dao.ResultDao;
import com.demo.online_quiz_system.entities.Result;
import com.demo.online_quiz_system.util.HibernateUtil;

public class ResultDaoImpl implements ResultDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Result saveResult(Result result) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(result);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result getResultById(int id) {
        Result result = null;
        try (Session session = sessionFactory.openSession()) {
            result = session.get(Result.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Result> getAllResults() {
        List<Result> results = null;
        try (Session session = sessionFactory.openSession()) {
            Query<Result> query = session.createQuery("FROM Result", Result.class);
            results = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public List<Result> getResultsByUserId(int userId) {
        List<Result> results = null;
        try (Session session = sessionFactory.openSession()) {
            Query<Result> query = session.createQuery("FROM Result WHERE user.id = :userId", Result.class);
            query.setParameter("userId", userId);
            results = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void updateResult(Result result) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(result);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteResult(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Result result = session.get(Result.class, id);
            if (result != null) {
                session.delete(result);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
