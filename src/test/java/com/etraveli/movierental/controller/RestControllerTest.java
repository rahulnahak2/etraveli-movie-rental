package com.etraveli.movierental.controller;

import com.etraveli.movierental.exceptions.InvalidRentalException;
import com.etraveli.movierental.exceptions.MovieNotFoundException;
import com.etraveli.movierental.models.CustomerRequest;
import com.etraveli.movierental.services.RentalInfo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GenerateSlipController.class)
public class RestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalInfo rentalInfo;

    // Mock successful statement generation
    @Test
    void testGenerateSlip_success() throws Exception {
        Mockito.when(rentalInfo.statement(any(CustomerRequest.class)))
                .thenReturn("Rental statement");

        mockMvc.perform(get("/movie-rental/generate-slip")
                        .contentType("application/json")
                        .content("""
                                {
                                    "name":"C. U. Stomer",
                                    "rentals":[{"movieId":"F001","days":3}]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().string("Rental statement"));
    }

    // Simulate exception when rental days are zero
    @Test
    void testCreateStatement_handlesInvalidRentalException() throws Exception {
        when(rentalInfo.statement(any()))
                .thenThrow(new InvalidRentalException("Days must be positive"));

        mockMvc.perform(get("/movie-rental/generate-slip")
                        .contentType("application/json")
                        .content("""
                        {
                          "name":"Test",
                          "rentals":[{"movieId":"F001","days":0}]
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation Failed"))
                .andExpect(jsonPath("$.message").value("Check the input fields"));
    }

    // Simulate exception when movie ID is not found
    @Test
    void testInvalidMovieIdException() throws Exception{
        when(rentalInfo.statement(any()))
                .thenThrow(new MovieNotFoundException("Movie Not Found"));
        mockMvc.perform(get("/movie-rental/generate-slip")
                        .contentType("application/json")
                        .content("""
                        {
                          "name":"C. U. Stomer",
                          "rentals":[{"movieId":"F0013","days":1}]
                        }
                        """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Movie Not Found"));
    }

    // Negative days rental for children's movie
    @Test
    void testZeroDaysRental_ChildrensMovie() throws Exception {
        when(rentalInfo.statement(any()))
                .thenThrow(new InvalidRentalException("Days must be positive"));

        mockMvc.perform(get("/movie-rental/generate-slip")
                        .contentType("application/json")
                        .content("""
                        {
                          "name":"Test",
                          "rentals":[{"movieId":"CH001","days":-1}]
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation Failed"));
    }

    // Negative days rental for regular movie
    @Test
    void testNegativeDaysRental_RegularMovie() throws Exception {
        when(rentalInfo.statement(any()))
                .thenThrow(new InvalidRentalException("Days must be positive"));

        mockMvc.perform(get("/movie-rental/generate-slip")
                        .contentType("application/json")
                        .content("""
                        {
                          "name":"Test",
                          "rentals":[{"movieId":"REG001","days":-2}]
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation Failed"));
    }

    // Negative days rental for new release
    @Test
    void testNegativeDaysRental_NewMovie() throws Exception {
        when(rentalInfo.statement(any()))
                .thenThrow(new InvalidRentalException("Days must be positive"));

        mockMvc.perform(get("/movie-rental/generate-slip")
                        .contentType("application/json")
                        .content("""
                        {
                          "name":"Test",
                          "rentals":[{"movieId":"NEW001","days":-5}]
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Validation Failed"));
    }
}
