package com.example.redis_basic_exam.caching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis_basic_exam.caching.dto.UserProfile;
import com.example.redis_basic_exam.caching.service.UserService;

@RestController
public class ApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{userId}/profile")
    public UserProfile getUserProfile(
            @PathVariable("userId") String userId) {
        return userService.getUserProfile(userId);
    }

}
