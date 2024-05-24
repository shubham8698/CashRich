package com.cashrich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.cashrich.model.User;
import com.cashrich.service.UserService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user, Authentication authentication) {
        String username = authentication.getName();
        User existingUser = (User) userService.findByUsername(username);
        if (existingUser == null) {
            return ResponseEntity.status(404).body("User not found");
        }
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setMobile(user.getMobile());
        if (user.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return ResponseEntity.ok(userService.updateUser((com.cashrich.model.User) existingUser));
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginUser, @RequestHeader("Origin") String origin) {
        // Basic validation
        if (loginUser.getUsername() == null || loginUser.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username and password are required.");
        }

        // Send predefined header values to confirm API origin
        if (!"27ab17d1-215f-49e5-9ca4-afd48810c149".equals(origin)) {
            return ResponseEntity.badRequest().body("Invalid API origin.");
        }

        // Authenticate user
        User user = userService.findByUsername(loginUser.getUsername());
        if (user == null || !userService.isPasswordMatching(loginUser.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }

        // If authentication successful, return user details or token
        return ResponseEntity.ok("Login successful for user: " + user.getUsername());
    }
}
