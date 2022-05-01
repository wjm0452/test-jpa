package com.jhelper.jserve.web.user;

import java.util.List;

import com.jhelper.jserve.web.entity.User;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Override
    @EntityGraph(attributePaths = { "org" })
    List<User> findAll();
}
