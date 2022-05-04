package com.jhelper.jserve.web.user;

import java.util.List;
import java.util.Optional;

import com.jhelper.jserve.web.entity.User;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Override
    @EntityGraph(attributePaths = { "org" })
    Optional<User> findById(String id);

    @Override
    @EntityGraph(attributePaths = { "org" })
    <S extends User> Optional<S> findOne(Example<S> example);

    @Query("select u " +
            "from User u " +
            "left join fetch u.org o")
    @Override
    <S extends User> List<S> findAll(Example<S> example);

    @Query("select u " +
            "from User u " +
            "join fetch u.org o " +
            "where u.usrNm like '%' || :name || '%' or o.orgNm like :name || '%'")
    List<User> findAllByName(@Param("name") String name);
}
