package com.example.carrental.controller;

import com.example.carrental.model.User;
import com.example.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public User create(@RequestBody User obj) {
        return service.create(obj);
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }
}