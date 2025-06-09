package com.example.carrental.model;

import jakarta.persistence.Entity;

@Entity
public class Customer extends User {

    private String driverLicenseNumber;

    public Customer() {
        super();
    }

    public Customer(String name, String idNumber, String contactInfo, String username, String password, String driverLicenseNumber) {
        super(name, idNumber, contactInfo, username, password);
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }
}