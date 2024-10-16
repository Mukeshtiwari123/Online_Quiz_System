package com.demo.online_quiz_system.Dao;

import java.util.List;
import com.demo.online_quiz_system.entities.Answers;

public interface AnswerDao {
	// Save a new answer
    Answers saveAnswer(Answers answer);

    // Get an answer by its ID
    Answers getAnswerById(int id);

    // Get all answers
    List<Answers> getAllAnswers();

    // Get answers by category ID
    List<Answers> getAnswersByCategoryId(int categoryId);

    // Update an existing answer
    void updateAnswer(Answers answer);

    // Delete an answer by its ID
    void deleteAnswer(int id);
	
}
