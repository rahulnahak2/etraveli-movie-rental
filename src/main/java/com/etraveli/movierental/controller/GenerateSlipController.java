package com.etraveli.movierental.controller;

import com.etraveli.movierental.entities.CustomerDetails;
import com.etraveli.movierental.models.CustomerRequest;
import com.etraveli.movierental.services.RentalInfo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-rental")
public class GenerateSlipController {

    private final RentalInfo rentalInfo;

    public GenerateSlipController(RentalInfo rentalInfo) {
        this.rentalInfo = rentalInfo;
    }

    @GetMapping ("/generate-slip")
    public ResponseEntity<String> getMovieRentalSlip(@Valid @RequestBody CustomerRequest customerDetails){
        return ResponseEntity.ok(rentalInfo.statement(customerDetails));
    }
}
