package com.demo.online_quiz_system.service;

import com.demo.online_quiz_system.entities.Answers; // Assuming you have an Answer entity
import java.util.List;

public interface AnswersService {
    Answers createAnswer(Answers answer);
    List<Answers> getAllAnswers();
    Answers getAnswerById(int answerId);
    Answers updateAnswer(int answerId, Answers updatedAnswer);
    String deleteAnswer(int answerId);
}
