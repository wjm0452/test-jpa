package com.jhelper.jserve.web.code;

import java.util.List;
import java.util.Optional;

import com.jhelper.jserve.web.entity.Lrgclas;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LargeCodeRepository extends JpaRepository<Lrgclas, String> {

    // @Override
    // @EntityGraph(attributePaths = { "smlclass" })
    // defult Optional<Lrgclas> findById(String id);

    // @Override
    // @EntityGraph(attributePaths = { "smlclass" })
    // List<Lrgclas> findAllById(Iterable<String> ids);
}
