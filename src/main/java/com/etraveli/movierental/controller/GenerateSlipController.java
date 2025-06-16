package com.etraveli.movierental.controller;

import com.etraveli.movierental.models.CustomerRequest;
import com.etraveli.movierental.services.RentalInfo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-rental") // Base URL for all endpoints in this controller
public class GenerateSlipController {

    private final RentalInfo rentalInfo;

    // Constructor injection of the RentalInfo service
    public GenerateSlipController(RentalInfo rentalInfo) {
        this.rentalInfo = rentalInfo;
    }

    @GetMapping ("/generate-slip")  // Maps HTTP GET requests to this method
    public ResponseEntity<String> getMovieRentalSlip(@Valid @RequestBody CustomerRequest customerDetails){
        // Validates the request body and invokes the rental service to generate a rental statement
        return ResponseEntity.ok(rentalInfo.statement(customerDetails));
    }
}
