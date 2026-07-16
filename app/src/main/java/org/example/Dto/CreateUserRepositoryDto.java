package org.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRepositoryDto {
    private long id;
    private String username;
    private String name;
    private boolean issuedBook;
}
