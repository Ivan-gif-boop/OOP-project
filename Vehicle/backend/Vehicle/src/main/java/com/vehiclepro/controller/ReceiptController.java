package com.example.carrental.controller;

import com.example.carrental.model.Receipt;
import com.example.carrental.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receipts")
@CrossOrigin(origins = "*")
public class ReceiptController {

    @Autowired
    private ReceiptService service;

    @PostMapping
    public Receipt create(@RequestBody Receipt obj) {
        return service.create(obj);
    }

    @GetMapping
    public List<Receipt> getAll() {
        return service.getAll();
    }
}