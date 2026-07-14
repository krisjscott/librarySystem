package org.example.ErrorHandling;

public class DuplicateResouceException extends  RuntimeException{
    public DuplicateResouceException(String message){
        super(message);
    }
}
