package org.example.ErrorHandling;

public class DuplicateResourceException extends  RuntimeException{
    public DuplicateResourceException(String message){
        super(message);
    }
}
