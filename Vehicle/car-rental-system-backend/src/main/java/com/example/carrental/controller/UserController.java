package com.example.carrental.controller;

import com.example.carrental.model.User;
import com.example.carrental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{username}")
    public Optional<User> getUser(@PathVariable String username) {
        return userService.findByUsername(username);
    }
}