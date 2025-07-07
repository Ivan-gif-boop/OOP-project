package com.example.carrental.controller;

import com.example.carrental.model.Payment;
import com.example.carrental.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping
    public Payment create(@RequestBody Payment obj) {
        return service.create(obj);
    }

    @GetMapping
    public List<Payment> getAll() {
        return service.getAll();
    }
}