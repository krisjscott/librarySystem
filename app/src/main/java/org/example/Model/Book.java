package org.example.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Book {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column
    private LocalDateTime createdAt;

    public Book() {}
    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return this.author; }
    public void setAuthor(String author) { this.author = author; }
    public LocalDateTime getCreatedAt() { return this.createdAt; }

    public void createAt(LocalDateTime now) {
        this.createdAt = now;
    }
}
