package com.kal.club.Exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super("RNFE: " + message);
    }
}
