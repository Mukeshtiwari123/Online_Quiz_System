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
    private int quizId;

    @Column(name = "title", nullable = false)
    private String title;

    // No cascading needed when deleting quiz
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    // No cascading needed when deleting quiz
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User createdBy;

    // Cascade only at quiz level; orphan removal to delete related entries
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Leaderboard> leaderboardEntries = new HashSet<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Result> results;

    // Constructor, getters, setters, other methods


	// Method to remove a specific Result
	public void removeResult(Result result) {
		results.remove(result);
		result.setQuiz(null);
	}



	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Categories getCategory() {
		return category;
	}
	public void setCategory(Categories category) {
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



	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Quiz(String title, Categories category, User createdBy, Set<Leaderboard> leaderboardEntries) {
		this.title = title;
		this.category = category;
		this.createdBy = createdBy;
		this.leaderboardEntries = leaderboardEntries;
	}

	@Override
	public String toString() {
		return "Quiz [title=" + title + ", category=" + category + ", createdBy=" + createdBy + ", leaderboardEntries="
				+ leaderboardEntries + "]";
	}



}
