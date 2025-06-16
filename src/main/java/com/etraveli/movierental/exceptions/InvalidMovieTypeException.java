package com.etraveli.movierental.exceptions;

public class InvalidMovieTypeException extends RuntimeException{
    public InvalidMovieTypeException(String message) {
        super(message);
    }
}
