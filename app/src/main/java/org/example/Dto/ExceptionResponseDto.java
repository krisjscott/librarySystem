package org.example.Dto;

import java.time.LocalDateTime;

public class ExceptionResponseDto {
    private LocalDateTime now;
    private int statusCode;
    private String reasonPhrase;
    private String message;
    private String path;
    public ExceptionResponseDto(LocalDateTime now, int statusCode, String reasonPhrase, String message, String path) {
        this.now = now;
        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getNow() {
        return now;
    }
    public void setNow(LocalDateTime now) {
        this.now = now;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getReasonPhrase() {
        return reasonPhrase;
    }
    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
