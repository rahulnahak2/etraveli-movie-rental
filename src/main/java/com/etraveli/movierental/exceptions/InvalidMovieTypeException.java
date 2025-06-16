package com.etraveli.movierental.exceptions;

// Custom exception class to handle invalid or unrecognized movie type codes
public class InvalidMovieTypeException extends RuntimeException{
    public InvalidMovieTypeException(String message) {
        super(message);
    }
}
