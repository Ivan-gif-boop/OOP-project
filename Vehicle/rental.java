package com.vehiclepro.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vehicle vehicle;

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;

    private String renterName;
    private String idNumber;
    private String contactInfo;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private boolean returned;

    @Column(length = 2000)
    private String digitalReceipt;
    
}
