package com.demo.online_quiz_system.service;

import com.demo.online_quiz_system.entities.Answers; // Assuming you have an Answer entity
import java.util.List;

public interface AnswersService {
	// Create a new answer record
    Answers createAnswer(Answers answer);

    // Retrieve all answer records
    List<Answers> getAllAnswers();

    // Retrieve an answer by its ID
    Answers getAnswerById(int answerId);

    // Update an existing answer record
    void updateAnswer(Answers answer);

    // Delete an answer by its ID
    void deleteAnswer(int answerId);
}
