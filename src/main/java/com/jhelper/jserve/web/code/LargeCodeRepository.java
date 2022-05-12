package com.jhelper.jserve.web.code;

import java.util.List;
import java.util.Optional;

import com.jhelper.jserve.web.entity.Lrgclas;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LargeCodeRepository extends JpaRepository<Lrgclas, String> {
    @Query("select l from Lrgclas l join fetch l.smlclass")
    List<Lrgclas> findAllWithSmlclass();

    @EntityGraph(attributePaths = "smlclass")
    Optional<Lrgclas> findWithSmlclassByLrgclasCd(String id);
}
