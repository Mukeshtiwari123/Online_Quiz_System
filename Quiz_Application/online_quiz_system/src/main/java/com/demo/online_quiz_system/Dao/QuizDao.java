package com.demo.online_quiz_system.Dao;

import java.util.List;
import com.demo.online_quiz_system.entities.Quiz;

public interface QuizDao {
    Quiz createQuiz(Quiz quiz);	
    List<Quiz> getAllQuizzes();
    Quiz getQuizById(int quizId);
    Quiz updateQuiz(int quizId, Quiz updatedQuiz);
    String deleteQuiz(int quizId);
}
