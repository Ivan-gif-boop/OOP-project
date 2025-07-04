package com.vehiclepro.repository;

import com.vehiclepro.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByVehicleId(Long vehicleId);
}

