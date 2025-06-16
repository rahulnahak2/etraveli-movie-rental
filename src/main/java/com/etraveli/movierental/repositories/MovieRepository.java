package com.etraveli.movierental.repositories;

import com.etraveli.movierental.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,String> {
}
