package com.etraveli.movierental.exceptions;

// Custom exception class to indicate that a movie with the given ID was not found
public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String message) {
        super(message);
    }
}
