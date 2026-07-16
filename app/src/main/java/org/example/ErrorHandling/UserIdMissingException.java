package org.example.ErrorHandling;

public class UserIdMissingException extends RuntimeException{
    public UserIdMissingException(String message){
        super(message);
    }
}
