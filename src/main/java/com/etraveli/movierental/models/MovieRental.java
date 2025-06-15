package com.etraveli.movierental.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieRental {
    private String movieId;
    private int days;
}
