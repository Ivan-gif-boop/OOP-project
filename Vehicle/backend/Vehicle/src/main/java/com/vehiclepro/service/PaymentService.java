package com.example.carrental.service;

import com.example.carrental.model.Payment;
import com.example.carrental.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public Payment create(Payment obj) {
        return repository.save(obj);
    }

    public List<Payment> getAll() {
        return repository.findAll();
    }
}