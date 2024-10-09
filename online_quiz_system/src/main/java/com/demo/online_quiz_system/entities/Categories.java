package com.demo.online_quiz_system.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    // One-to-many relationship with Quiz
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Quiz> quizzes;

    // Getters and Setters
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

	public Categories(String name, String description, Set<Quiz> quizzes) {
		super();
		//this.categoryId = categoryId;
		this.name = name;
		this.description = description;
		this.quizzes = quizzes;
	}

	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Categories [categoryId=" + categoryId + ", name=" + name + ", description=" + description + "]";
	}

	




}
