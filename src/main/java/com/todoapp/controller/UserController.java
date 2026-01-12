package com.todoapp.controller;

import com.todoapp.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    public Object getProfile(HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "❌ Unauthorized: Please login first";
        }

        return user;
    }
}
