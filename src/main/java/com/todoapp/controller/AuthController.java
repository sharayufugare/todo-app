package com.todoapp.controller;

import com.todoapp.model.User;
import com.todoapp.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // ✅ REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {

        if (user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Username & Password required");
        }

        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpSession session) {

        User existingUser =
                userRepository.findByUsernameAndPassword(
                        user.getUsername(),
                        user.getPassword()
                );

        if (existingUser == null) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        session.setAttribute("loggedInUser", existingUser);
        return ResponseEntity.ok("Login successful");
    }

    // ✅ LOGOUT
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }
}