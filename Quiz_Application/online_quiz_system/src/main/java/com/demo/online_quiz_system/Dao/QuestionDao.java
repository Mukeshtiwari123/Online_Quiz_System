package com.demo.online_quiz_system.Dao;

import java.util.List;
import com.demo.online_quiz_system.entities.Question;

public interface QuestionDao {
	 // Save a new question
    Question saveQuestion(Question question);

    // Get a question by ID
    Question getQuestionById(int id);

    // Get all questions
    List<Question> getAllQuestions();

    // Update an existing question
    void updateQuestion(Question question);

    // Delete a question by ID
    void deleteQuestion(int id);
}
