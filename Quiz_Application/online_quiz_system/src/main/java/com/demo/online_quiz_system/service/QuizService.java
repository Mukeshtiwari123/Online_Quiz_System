package com.demo.online_quiz_system.service;

import com.demo.online_quiz_system.entities.Quiz;
import java.util.List;

public interface QuizService {
    Quiz createQuiz(Quiz quiz);
    List<Quiz> getAllQuizzes();
    Quiz getQuizById(int quizId);
    Quiz updateQuiz(int quizId, Quiz updatedQuiz);
    String deleteQuiz(int quizId);
}
