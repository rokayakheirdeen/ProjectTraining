package com.dgpad.project.model;

import com.dgpad.project.model.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content; // The feedback message

    private int rating; // Optional: Add a rating feature (1-5 stars)

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // The user who gave the feedback

    private LocalDateTime createdAt; // The date the feedback was submitted

    public Feedback() {
        this.createdAt = LocalDateTime.now(); // Automatically set feedback time
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
