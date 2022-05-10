package com.jhelper.jserve.web.user;

import java.util.List;

import com.jhelper.jserve.web.entity.Org;
import com.jhelper.jserve.web.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }

    // public List<User> getUsersByName(String name) {
    //     return userRepository.findAllByName(name);
    // }
}
