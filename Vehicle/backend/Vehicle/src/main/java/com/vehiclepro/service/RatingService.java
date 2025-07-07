package com.vehiclepro.service;

import com.vehiclepro.model.Rating;
import com.vehiclepro.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public List<Rating> getRatingsByVehicleId(Long vehicleId) {
        return ratingRepository.findByVehicleId(vehicleId);
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }
}
