package org.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRepositoryDto {
    private Long id;
    private String title;
    private String author;
}

//    public CreateBookRepositoryDto(Long id, String title, String author) {
//        this.id = id;
//        this.title = title;
//        this.author = author;
//    }
//
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public String getTitle() { return title; }
//    public void setTitle(String title) { this.title = title; }
//
//    public String getAuthor() { return author; }
//    public void setAuthor(String author) { this.author = author; }
//}
