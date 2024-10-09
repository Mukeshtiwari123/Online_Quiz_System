package com.demo.online_quiz_system.entities;

import javax.persistence.*;

@Entity
@Table(name = "leaderboard") // Ensure the table is created with the correct name
public class Leaderboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leaderboard_id")
    private int leaderboardId;

    // Many-to-one relationship with Quiz, assuming a quiz can have multiple entries on the leaderboard
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false) // Foreign key to the quiz table
    private Quiz quiz;

    // Many-to-one relationship with User, assuming a user can appear multiple times in different quizzes
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to the user table
    private User user;

    @Column(name = "score", nullable = false)
    private double score;

    @Column(name = "ranking", nullable = false)  // Renamed column to avoid using 'rank'
    private int ranking;

    // Getters and Setters
    public int getLeaderboardId() {
        return leaderboardId;
    }

    public void setLeaderboardId(int leaderboardId) {
        this.leaderboardId = leaderboardId;
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

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }


    // Constructor Using Fields
    public Leaderboard(Quiz quiz, User user, double score, int ranking) {
        this.quiz = quiz;
        this.user = user;
        this.score = score;
        this.ranking = ranking;
    }

    // Default Constructor
    public Leaderboard() {}

    // ToString Method
    @Override
    public String toString() {
        return "[Leaderboard ID: " + leaderboardId + ", Rank: " + ranking + ", Score: " + score+"]";
    }
}
