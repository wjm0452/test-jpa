package com.jhelper.jserve.web.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.jhelper.jserve.web.entity.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {

    @Autowired
    UserService userService;

    @Test
    void testName() {

        String name = "AD";

        List<User> users = userService.getUsersByName(name);

        users.forEach(user -> {
            boolean contain = user.getUsrNm().contains(name) || user.getOrg().getOrgNm().contains(name);
            assertThat(contain).isTrue();
        });

    }

}
