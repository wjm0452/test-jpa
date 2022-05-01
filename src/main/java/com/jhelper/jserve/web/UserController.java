package com.jhelper.jserve.web;

import java.util.List;

import com.jhelper.jserve.web.entity.User;
import com.jhelper.jserve.web.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> allUsers() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User user(@PathVariable String userId) {
        return userService.findById(userId);
    }

    @GetMapping("/{userId}/photo")
    public User userPhoto(@PathVariable String userId) {
        return userService.findById(userId);
    }
}
