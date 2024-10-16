package com.demo.online_quiz_system.service;

import com.demo.online_quiz_system.entities.Question;
import java.util.List;

public interface QuestionService {
	// Create a new question
    Question createQuestion(Question question);

    // Retrieve all questions
    List<Question> getAllQuestions();

    // Retrieve a question by its ID
    Question getQuestionById(int questionId);

    // Update an existing question
    void updateQuestion(Question question);

    // Delete a question by its ID
    void deleteQuestion(int questionId);
}
