package com.demo.online_quiz_system.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Answers")
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", length = 10)
    private Integer answer_id;

    @Column(name = "category_id", length = 10)
    private String  category_id;

    @Column(name = "category_name", length = 100)
    private String category_name;

    @Column(name = "question", length = 255)
    private String question;

    @Column(name = "correct_answer", length = 100)
    private String correct_answer;

    @Column(name = "user_answer", length = 100)
    private String user_answer; // Store the answer provided by the user

    // Getter and Setter methods
    public Integer getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Integer answer_id) {
        this.answer_id = answer_id;
    }

    public String  getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String  string) {
        this.category_id = string;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getUser_answer() {
        return user_answer;
    }

    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
    }

    // All-argument constructor
    public Answers( String category_name, String question, String correct_answer, String user_answer) {
        //this.category_id = category_id;
        this.category_name = category_name;
        this.question = question;
        this.correct_answer = correct_answer;
        this.user_answer = user_answer;
    }

    // Default constructor
    public Answers() {
    }

    // ToString method
    @Override
    public String toString() {
        return "Answers [category_id=" + category_id + ", category_name=" + category_name +
                ", question=" + question + ", correct_answer=" + correct_answer +
                ", user_answer=" + user_answer + "]";
    }
}
