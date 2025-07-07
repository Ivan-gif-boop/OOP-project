package com.vehiclepro.repository;

import com.vehiclepro.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    // You can add custom queries later if needed!
}
