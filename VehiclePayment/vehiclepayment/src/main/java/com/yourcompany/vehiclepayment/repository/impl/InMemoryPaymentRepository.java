package com.yourcompany.vehiclepayment.repository.impl;

import com.yourcompany.vehiclepayment.model.Payment;
import com.yourcompany.vehiclepayment.repository.PaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryPaymentRepository implements PaymentRepository {
    private final Map<String, Payment> payments = new ConcurrentHashMap<>();

    @Override
    public Payment save(Payment payment) {
        payments.put(payment.getTransactionId(), payment);
        return payment;
    }
    

    @Override
    public Optional<Payment> findByTransactionId(String transactionId) {
        return Optional.ofNullable(payments.get(transactionId));
    }

    @Override
    public List<Payment> findByUserId(Long userId) {
        return payments.values().stream()
            .filter(payment -> payment.getUserId().equals(userId))
            .collect(Collectors.toList());
    }

    @Override
    public List<Payment> findByVehicleId(Long vehicleId) {
        return payments.values().stream()
            .filter(payment -> payment.getVehicleId().equals(vehicleId))
            .collect(Collectors.toList());
    }

    @Override
    public List<Payment> findByRentalId(String rentalId) {
        return payments.values().stream()
            .filter(payment -> Objects.equals(payment.getRentalId(), rentalId))
            .collect(Collectors.toList());
}
}  