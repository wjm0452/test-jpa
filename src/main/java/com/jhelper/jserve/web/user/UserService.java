package com.jhelper.jserve.web.user;

import java.util.List;

import com.jhelper.jserve.web.entity.Org;
import com.jhelper.jserve.web.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        users.stream().forEach(user -> {
            Org org = user.getOrg();
            if (org != null) {
                System.out.println(org.getOrgCd());
            }
        });
        return null;
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }
}
