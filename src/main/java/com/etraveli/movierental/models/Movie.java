package com.etraveli.movierental.models;

import com.etraveli.movierental.services.enums.MovieType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    private String title;
    private MovieType code;
}
