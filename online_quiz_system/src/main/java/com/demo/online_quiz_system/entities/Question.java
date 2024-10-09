package com.demo.online_quiz_system.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private int questionId;

	@Column(name = "question_text", nullable = false)
	private String questionText;

	@Enumerated(EnumType.STRING)
	@Column(name = "question_type", nullable = false)
	private QuestionType questionType;

	// Enum for question types
	public enum QuestionType {
		MULTIPLE_CHOICE, TRUE_FALSE, SHORT_ANSWER
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

//	public Quiz getQuiz() {
//		return quiz;
//	}
//
//	public void setQuiz(Quiz quiz) {
//		this.quiz = quiz;
//	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public Question(Quiz quiz, String questionText, QuestionType questionType) {
		super();
		//this.questionId = questionId;
		//this.quiz = quiz;
		this.questionText = questionText;
		this.questionType = questionType;
	}

	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionText=" + questionText + ", questionType="
				+ questionType + "]";
	}

	

}
