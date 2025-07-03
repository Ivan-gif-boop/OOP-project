package com.example.carrental.model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    private String adminLevel;

    public Admin() {
        super();
    }

    public Admin(String name, String idNumber, String contactInfo, String username, String password, String adminLevel) {
        super(name, idNumber, contactInfo, username, password);
        this.adminLevel = adminLevel;
    }

    public String getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }
}