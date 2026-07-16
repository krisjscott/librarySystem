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
    private boolean issuedBy;

}

