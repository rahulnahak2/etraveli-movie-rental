package com.etraveli.movierental.configuration;

import com.etraveli.movierental.models.Movie;
import com.etraveli.movierental.services.enums.MovieType;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MovieCatalog {
    private final HashMap<String, Movie> movies = new HashMap<>();

    public MovieCatalog() {
        movies.put("F001", new Movie("You've Got Mail", MovieType.REGULAR));
        movies.put("F002", new Movie("Matrix", MovieType.REGULAR));
        movies.put("F003", new Movie("Cars", MovieType.CHILDRENS));
        movies.put("F004", new Movie("Fast & Furious X", MovieType.NEW));
    }

    public Movie getMovieById(String id) {
        return movies.get(id);
    }
}
