package com.vehiclepro.repository;

import com.vehiclepro.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;  //JpaRepository provides CRUD operations to entities
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByAvailableTrue();
}
