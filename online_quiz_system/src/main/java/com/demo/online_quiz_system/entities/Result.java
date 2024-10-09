package com.demo.online_quiz_system.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "result_id")
	private int resultId;

	@ManyToOne // No cascade
	@JoinColumn(name = "quiz_id", nullable = false)
	private Quiz quiz;

	@ManyToOne // No cascade
	@JoinColumn(name = "user_id", nullable = false)
	private User user;



	@Column(name = "score", nullable = false)
	private double score;

	@Column(name = "taken_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date takenAt;


	// Automatically set the current timestamp before persisting the entity
	@PrePersist
	protected void onCreate() {
		takenAt = new Date();
	}

	// Getter and Setter methods
	public int getResultId() {
		return resultId;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Date getTakenAt() {
		return takenAt;
	}

	public void setTakenAt(Date takenAt) {
		this.takenAt = takenAt;
	}

	// Constructor without resultId, takenAt is handled by the DB
	public Result(Quiz quiz, User user, double score) {
		this.quiz = quiz;
		this.user = user;
		this.score = score;
		this.takenAt=new Date();  //Set the current timestamp
	}

	// No-args constructor for Hibernate
	public Result() {
		super();
	}

	@Override
	public String toString() {
		return "Result [resultId=" + resultId + ", quizId=" + quiz.getQuizId() + ", userId=" + user.getUserId() 
		+ ", score=" + score + ", takenAt=" + takenAt + "]";
	}

}
