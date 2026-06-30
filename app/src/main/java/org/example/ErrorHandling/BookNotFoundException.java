package org.example.ErrorHandling;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("BOOK NOT FOUND:");
    }
}
