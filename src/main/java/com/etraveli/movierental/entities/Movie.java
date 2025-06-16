package com.etraveli.movierental.entities;

import com.etraveli.movierental.services.enums.MovieType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id // // Marks 'id' as the primary key
    private String id;
    private String title;   // Movie title
    @Enumerated(EnumType.STRING)
    // Specifies that the MovieType enum should be persisted as a string (e.g., "REGULAR") instead of its ordinal
    private MovieType code; // Enum indicating the movie's category (CHILDRENS, REGULAR, NEW)
}
