package com.etraveli.movierental.exceptions;

public class InvalidRentalException extends RuntimeException{
    public InvalidRentalException(String message) {
        super(message);
    }
}
