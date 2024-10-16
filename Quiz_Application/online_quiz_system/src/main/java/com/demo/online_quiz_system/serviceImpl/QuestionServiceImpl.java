package com.demo.online_quiz_system.serviceImpl;

import java.util.List;
import com.demo.online_quiz_system.Dao.QuestionDao; // Assuming you have QuestionDao
import com.demo.online_quiz_system.DaoImpl.QuestionDaoImpl; // Assuming you have QuestionDaoImpl
import com.demo.online_quiz_system.entities.Question;
import com.demo.online_quiz_system.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {

	// Dependency on QuestionDao
    private QuestionDao questionDao = new QuestionDaoImpl();

    @Override
    public Question createQuestion(Question question) {
        return questionDao.saveQuestion(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

    @Override
    public Question getQuestionById(int questionId) {
        return questionDao.getQuestionById(questionId);
    }

    @Override
    public void updateQuestion(Question question) {
        questionDao.updateQuestion(question);
    }

    @Override
    public void deleteQuestion(int questionId) {
        questionDao.deleteQuestion(questionId);
    }
}
