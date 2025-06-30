package com.example.resumeAnalizer.controller;

import com.example.resumeAnalizer.dto.UserDto;
import com.example.resumeAnalizer.model.User;
import com.example.resumeAnalizer.repository.UserRepository;

import jakarta.validation.Valid;

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

    Optional<User> existingUser = userRepository.findByEmailIgnoreCase(email);

    if (existingUser.isPresent()) {
        User dbUser = existingUser.get();
        if (passwordEncoder.matches(password, dbUser.getPassword())) {
            UserDto dto = new UserDto(dbUser.getId(), dbUser.getEmail());
            return ResponseEntity.ok(dto); // ✅ Return only ID and Email
        }
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
}

    // ✅ REGISTRATION
@PostMapping("/register")
public ResponseEntity<?> register(@Valid @RequestBody User user) {
    if (userRepository.findByEmail(user.getEmail()).isPresent()) {
        return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
               .body("Email already registered");
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return ResponseEntity.ok("Registration successful");
}
}
