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

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        users.stream().forEach(user -> {
            Org org = user.getOrg();
            if (org != null) {
                System.out.println(org.getOrgCd());
            }
        });
        return null;
    }

    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}
