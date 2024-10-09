package com.demo.online_quiz_system.service;

import com.demo.online_quiz_system.entities.Question;
import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);
    List<Question> getAllQuestions();
    Question getQuestionById(int questionId);
    Question updateQuestion(int questionId, Question updatedQuestion);
    String deleteQuestion(int questionId);
}
