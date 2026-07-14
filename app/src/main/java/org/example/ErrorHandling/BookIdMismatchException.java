package org.example.ErrorHandling;

public class BookIdMismatchException extends RuntimeException {
    public BookIdMismatchException() {
        super("Mismatched Book ID");
    }
}
