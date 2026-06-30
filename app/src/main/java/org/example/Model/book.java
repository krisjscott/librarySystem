package org.example.Model;

import jakarta.persistence.*;


@Entity
public class book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String author;

    public book() {}

    public Long getId() { return this.id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }

    public String getAuthor() { return this.author; }
    public void setAuthor(String author) { this.author = author; }
}
