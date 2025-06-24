package com.example.resumeAnalizer.controller;

import com.example.resumeAnalizer.model.User;
import com.example.resumeAnalizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

   @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user) {
    String email = user.getEmail().trim();
    String password = user.getPassword().trim();
    
    Optional<User> existingUser = userRepository.findByEmailAndPassword(email, password);
    
    if (existingUser.isPresent()) {
        return ResponseEntity.ok("Login successful");
    } else {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}


    // ✅ REGISTRATION
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("Registration successful");
    }
}
