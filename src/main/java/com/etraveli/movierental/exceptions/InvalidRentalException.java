package com.etraveli.movierental.exceptions;

// Custom exception class for handling invalid rental scenarios,
// such as zero or negative rental days
public class InvalidRentalException extends RuntimeException{
    public InvalidRentalException(String message) {
        super(message);
    }
}
