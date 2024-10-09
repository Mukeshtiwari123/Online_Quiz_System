package com.demo.online_quiz_system.Dao;

import java.util.List;
import com.demo.online_quiz_system.entities.Answers;

public interface AnswerDao {
	Answers createAnswer(Answers answer);	
    List<Answers> getAllAnswers();
    Answers getAnswerById(int answerId);
    Answers updateAnswer(int answerId, Answers updatedAnswer);
    String deleteAnswer(int answerId);
	
}
