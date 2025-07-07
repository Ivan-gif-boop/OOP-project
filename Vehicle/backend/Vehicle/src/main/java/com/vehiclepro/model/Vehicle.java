package com.vehiclepro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    private String size;

    @Column(unique = true, nullable = false)
    private String plateNumber;

    private boolean available = true;

    // Constructors
    public Vehicle() {}

    public Vehicle(String make, String model, String size, String plateNumber, boolean available) {
        this.make = make;
        this.model = model;
        this.size = size;
        this.plateNumber = plateNumber;
        this.available = available;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
