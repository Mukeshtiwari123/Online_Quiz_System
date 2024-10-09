package com.demo.online_quiz_system.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	// One-to-many relationship with Quiz
	@OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Quiz> createdQuizzes = new HashSet<>();

	// One-to-many relationship with Result
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Result> results = new HashSet<>();

	// One-to-many relationship with Leaderboard
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Leaderboard> leaderboardEntries = new HashSet<>();


	// Getters and Setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Quiz> getCreatedQuizzes() {
		return createdQuizzes;
	}

	public void setCreatedQuizzes(Set<Quiz> createdQuizzes) {
		this.createdQuizzes = createdQuizzes;
	}

	public Set<Result> getResults() {
		return results;
	}

	public void setResults(Set<Result> results) {
		this.results = results;
	}

	public Set<Leaderboard> getLeaderboardEntries() {
		return leaderboardEntries;
	}

	public void setLeaderboardEntries(Set<Leaderboard> leaderboardEntries) {
		this.leaderboardEntries = leaderboardEntries;
	}

	// Constructors
	public User() {}

	public User(String username, String password, Set<Quiz> createdQuizzes, Set<Result> results, Set<Leaderboard> leaderboardEntries) {
		// this.userId = userId;
		this.username = username;
		this.password = password;
		this.createdQuizzes = createdQuizzes;
		this.results = results;
		this.leaderboardEntries = leaderboardEntries;
	}

	// toString method

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + username + ", password=" + password + "]";
	}

}
