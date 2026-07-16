package org.example.ErrorHandling;

public class BookIdMissingException extends RuntimeException{
    public BookIdMissingException(String message){
        super(message);
    }
}
