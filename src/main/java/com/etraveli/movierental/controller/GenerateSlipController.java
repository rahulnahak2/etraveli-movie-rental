package com.etraveli.movierental.controller;

import com.etraveli.movierental.models.CustomerDetails;
import com.etraveli.movierental.services.RentalInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-rental")
public class GenerateSlipController {
    @GetMapping ("/generate-slip")
    public String getMovieRentalSlip(@RequestBody CustomerDetails customerDetails){
        return new RentalInfo().statement(customerDetails);
    }
}
