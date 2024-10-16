package com.demo.online_quiz_system.serviceImpl;

import com.demo.online_quiz_system.Dao.QuizDao;
import com.demo.online_quiz_system.DaoImpl.QuizDaoImpl;
import com.demo.online_quiz_system.entities.Quiz;
import com.demo.online_quiz_system.service.QuizService;

import java.util.List;

public class QuizServiceImpl implements QuizService {
    private QuizDao quizDao=new QuizDaoImpl();

//    public QuizServiceImpl(QuizDao quizDao) {
//        this.quizDao = quizDao;
//    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizDao.createQuiz(quiz);
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizDao.getAllQuizzes();
    }

    @Override
    public Quiz getQuizById(int quizId) {
        return quizDao.getQuizById(quizId);
    }

    @Override
    public Quiz updateQuiz(int quizId, Quiz updatedQuiz) {
        return quizDao.updateQuiz(quizId, updatedQuiz);
    }

    @Override
    public String deleteQuiz(int quizId) {
        return quizDao.deleteQuiz(quizId);
    }
}
