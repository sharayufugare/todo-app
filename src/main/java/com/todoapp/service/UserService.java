//package com.todoapp.service;
//
//import com.todoapp.model.User;
//import com.todoapp.repository.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepo;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
//        this.userRepo = userRepo;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public void register(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepo.save(user);
//    }
//
//}
