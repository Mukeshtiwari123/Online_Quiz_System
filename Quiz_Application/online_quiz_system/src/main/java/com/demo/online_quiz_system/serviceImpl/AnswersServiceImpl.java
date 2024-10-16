package com.demo.online_quiz_system.serviceImpl;

import java.util.List;
import com.demo.online_quiz_system.Dao.AnswerDao;
import com.demo.online_quiz_system.DaoImpl.AnswerDaoImpl; 
import com.demo.online_quiz_system.entities.Answers;
import com.demo.online_quiz_system.service.AnswersService;

public class AnswersServiceImpl implements AnswersService {

    // Dependency on AnswerDao
    private AnswerDao answerDao = new AnswerDaoImpl();

    @Override
    public Answers createAnswer(Answers answer) {
        return answerDao.saveAnswer(answer);
    }

    @Override
    public List<Answers> getAllAnswers() {
        return answerDao.getAllAnswers();
    }

    @Override
    public Answers getAnswerById(int answerId) {
        return answerDao.getAnswerById(answerId);
    }

    @Override
    public void updateAnswer(Answers answer) {
        answerDao.updateAnswer(answer);
    }

    @Override
    public void deleteAnswer(int answerId) {
        answerDao.deleteAnswer(answerId);
    }
}