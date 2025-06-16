package com.etraveli.movierental.repositories;

import com.etraveli.movierental.entities.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDetails,String> {
}
