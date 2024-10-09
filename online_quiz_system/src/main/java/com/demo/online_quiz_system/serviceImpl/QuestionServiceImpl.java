package com.demo.online_quiz_system.serviceImpl;

import java.util.List;
import com.demo.online_quiz_system.Dao.QuestionDao; // Assuming you have QuestionDao
import com.demo.online_quiz_system.DaoImpl.QuestionDaoImpl; // Assuming you have QuestionDaoImpl
import com.demo.online_quiz_system.entities.Question;
import com.demo.online_quiz_system.service.QuestionService;

public class QuestionServiceImpl implements QuestionService {

    // Dependency on QuestionDao
    QuestionDao questionDao = new QuestionDaoImpl();

    @Override
    public Question createQuestion(Question question) {
        // Invoke QuestionDaoImpl method to save question object
        return questionDao.createQuestion(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        // Invoke QuestionDaoImpl method to retrieve all questions
        return questionDao.getAllQuestions();
    }

    @Override
    public Question getQuestionById(int questionId) {
        // Invoke QuestionDaoImpl method to retrieve a specific question by questionId
        return questionDao.getQuestionById(questionId);
    }

    @Override
    public Question updateQuestion(int questionId, Question updatedQuestion) {
        // Invoke QuestionDaoImpl method to update the question's details
        return questionDao.updateQuestion(questionId, updatedQuestion);
    }

    @Override
    public String deleteQuestion(int questionId) {
        // Invoke QuestionDaoImpl method to delete a question by questionId
        return questionDao.deleteQuestion(questionId);
    }
}
