package com.etraveli.movierental.models;

import lombok.Data;

@Data
public class MovieRentalRequest {
    private String movieId;
    private int days;
}
