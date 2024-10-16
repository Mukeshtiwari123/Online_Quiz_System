package com.demo.online_quiz_system.entities;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "categories")
@Access(AccessType.FIELD)
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId; // Change to Long for consistency with ID generation

    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    // One-to-many relationship with Quiz
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Quiz> quizzes;

    // Constructors, Getters, and Setters
    public Categories(String name, String description, Set<Quiz> quizzes) {
        this.name = name;
        this.description = description;
        this.quizzes = quizzes;
    }

    public Categories() {}

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
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

    @Override
    public String toString() {
        return "Categories [categoryId=" + categoryId + ", name=" + name + ", description=" + description + "]";
    }
}
