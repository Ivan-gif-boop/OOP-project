package com.example.carrental.service;

import com.example.carrental.model.Receipt;
import com.example.carrental.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository repository;

    public Receipt create(Receipt obj) {
        return repository.save(obj);
    }

    public List<Receipt> getAll() {
        return repository.findAll();
    }
}
