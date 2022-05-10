package com.jhelper.jserve.web.user;

import java.util.List;
import java.util.Optional;

import com.jhelper.jserve.web.entity.User;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @EntityGraph(attributePaths = "org")
    @Override
    Optional<User> findById(String id);

    @Override
    @EntityGraph(attributePaths = "org")
    List<User> findAllById(Iterable<String> ids);

    @Override
    @EntityGraph(attributePaths = "org")
    List<User> findAll();

    @Override
    @EntityGraph(attributePaths = "org")
    <S extends User> List<S> findAll(Example<S> example);

    @Override
    @EntityGraph(attributePaths = "org")
    <S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = "org")
    <S extends User> List<S> findAll(Example<S> example, Sort sort);
}
