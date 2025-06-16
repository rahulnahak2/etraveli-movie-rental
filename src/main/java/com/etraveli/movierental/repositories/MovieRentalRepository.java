package com.etraveli.movierental.repositories;

import com.etraveli.movierental.entities.MovieRental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRentalRepository extends JpaRepository<MovieRental,String> {
}
