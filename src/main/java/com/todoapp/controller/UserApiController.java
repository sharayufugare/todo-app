//package com.todoapp.controller;
//
//import com.todoapp.model.User;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class UserApiController {
//
//    @GetMapping("/api/user/profile")
//    public User getProfile(HttpSession session) {
//
//        User user = (User) session.getAttribute("loggedUser");
//
//        if (user == null) {
//            throw new RuntimeException("User not logged in");
//        }
//
//        return user;
//    }
//}
