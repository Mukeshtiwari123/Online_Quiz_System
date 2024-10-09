package com.demo.online_quiz_system.Dao;

import java.util.List;
import com.demo.online_quiz_system.entities.Question;

public interface QuestionDao {
    Question createQuestion(Question question);	
    List<Question> getAllQuestions();
    Question getQuestionById(int questionId);
    Question updateQuestion(int questionId, Question updatedQuestion);
    String deleteQuestion(int questionId);
}
