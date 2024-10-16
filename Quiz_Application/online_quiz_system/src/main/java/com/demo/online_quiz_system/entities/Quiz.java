package com.demo.online_quiz_system.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long quizId;

    @Column(name = "title", nullable = false)
    private String title;

    // Many-to-one relationship with Categories
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) // Foreign key to Categories
    private Categories category; // Change from String to Categories

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User createdBy;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Leaderboard> leaderboardEntries = new HashSet<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Result> results;

    // Constructors, Getters, and Setters
    public Quiz() {}

    public Quiz(String title, Categories category, User createdBy, Set<Leaderboard> leaderboardEntries) {
        this.title = title;
        this.category = category; // Use Categories type
        this.createdBy = createdBy;
        this.leaderboardEntries = leaderboardEntries;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Categories getCategory() { // Return Categories instead of String
        return category;
    }

    public void setCategory(Categories category) { // Accept Categories instead of String
        this.category = category;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Set<Leaderboard> getLeaderboardEntries() {
        return leaderboardEntries;
    }

    public void setLeaderboardEntries(Set<Leaderboard> leaderboardEntries) {
        this.leaderboardEntries = leaderboardEntries;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    // Method to remove a specific Result
    public void removeResult(Result result) {
        results.remove(result);
        result.setQuiz(null);
    }

    @Override
    public String toString() {
        return "Quiz [title=" + title + ", category=" + category + ", createdBy=" + createdBy + ", leaderboardEntries="
                + leaderboardEntries + "]";
    }
}
