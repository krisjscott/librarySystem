package org.example.ErrorHandling;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public class BookNotFoundException extends RuntimeException {
    private String  messages;
    public BookNotFoundException(String messages) {
        super(messages);
    }

    public String getMessages() {
        return messages;
    }

}
