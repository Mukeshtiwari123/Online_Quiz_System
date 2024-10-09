package com.demo.online_quiz_system.serviceImpl;

import java.util.List;
import com.demo.online_quiz_system.Dao.AnswerDao;
import com.demo.online_quiz_system.DaoImpl.AnswerDaoImpl; 
import com.demo.online_quiz_system.entities.Answers;
import com.demo.online_quiz_system.service.AnswersService;

public class AnswersServiceImpl implements AnswersService {

    // Dependency on AnswersDao
    AnswerDao answersDao = new AnswerDaoImpl();

    @Override
    public Answers createAnswer(Answers answer) {
        // Invoke AnswersDaoImpl method to save answer object
        return answersDao.createAnswer(answer);
    }

    @Override
    public List<Answers> getAllAnswers() {
        // Invoke AnswersDaoImpl method to retrieve all answers
        return answersDao.getAllAnswers();
    }

    @Override
    public Answers getAnswerById(int answerId) {
        // Invoke AnswersDaoImpl method to retrieve a specific answer by answerId
        return answersDao.getAnswerById(answerId);
    }

    @Override
    public Answers updateAnswer(int answerId, Answers updatedAnswer) {
        // Invoke AnswersDaoImpl method to update the answer's details
        return answersDao.updateAnswer(answerId, updatedAnswer);
    }

    @Override
    public String deleteAnswer(int answerId) {
        // Invoke AnswersDaoImpl method to delete an answer by answerId
        return answersDao.deleteAnswer(answerId);
    }
}
