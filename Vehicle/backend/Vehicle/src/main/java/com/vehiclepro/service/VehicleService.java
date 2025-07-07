package com.example.carrental.service;

import com.example.carrental.model.Vehicle;
import com.example.carrental.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public Vehicle create(Vehicle obj) {
        return repository.save(obj);
    }

    public List<Vehicle> getAll() {
        return repository.findAll();
    }
}
