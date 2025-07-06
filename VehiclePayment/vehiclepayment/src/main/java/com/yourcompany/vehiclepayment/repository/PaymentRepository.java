package com.yourcompany.vehiclepayment.repository;

import com.yourcompany.vehiclepayment.model.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findByTransactionId(String transactionId);
    List<Payment> findByUserId(Long userId);
    List<Payment> findByVehicleId(Long vehicleId);
    List<Payment> findByRentalId(String rentalId);
}