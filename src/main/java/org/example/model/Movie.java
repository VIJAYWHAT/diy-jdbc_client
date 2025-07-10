package org.example.model;

import jakarta.persistence.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String title;

    @Column(name = "directed_by", length = 80)
    private String directedBy;

    public Movie() {}

    public Movie(String title, String directedBy) {
        this.title = title;
        this.directedBy = directedBy;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDirectedBy() { return directedBy; }
    public void setDirectedBy(String directedBy) { this.directedBy = directedBy; }
}