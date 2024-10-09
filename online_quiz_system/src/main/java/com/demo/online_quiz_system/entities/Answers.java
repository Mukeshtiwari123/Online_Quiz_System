package com.demo.online_quiz_system.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "answer_id", length = 10)
	private int answer_id;


	@Column(name = "question_id", length = 10)
	private String question_id;


	@Column(name = "answer_text", length = 100)
	private String answer_text;


	@Column(name = "is_correct", length = 10)
	private String is_correct;

	//Getter And Setter Methods
	public int getAnswer_id() {
		return answer_id;
	}

	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}

	public String getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(String question_id) {
		this.question_id = question_id;
	}

	public String getAnswer_text() {
		return answer_text;
	}

	public void setAnswer_text(String answer_text) {
		this.answer_text = answer_text;
	}

	public String getIs_correct() {
		return is_correct;
	}

	public void setIs_correct(String is_correct) {
		this.is_correct = is_correct;
	}

	//All argument Constructor
	public Answers( String question_id, String answer_text, String is_correct) {
		super();
		//		this.answer_id = answer_id;
		this.question_id = question_id;
		this.answer_text = answer_text;
		this.is_correct = is_correct;
	}
	//Default Constructor
	public Answers() {
		super();
	}

	//ToString method
	@Override
	public String toString() {
		return "Answers [question_id=" + question_id + ", answer_text=" + answer_text + ", is_correct=" + is_correct
				+ "]";
	}





}
