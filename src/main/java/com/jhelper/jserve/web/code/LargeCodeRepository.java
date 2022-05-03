package com.jhelper.jserve.web.code;

import java.util.List;

import com.jhelper.jserve.web.entity.LrgclasCd;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LargeCodeRepository extends JpaRepository<LrgclasCd, String> {

    @Override
    @EntityGraph(attributePaths = { "smlclasCds" })
    List<LrgclasCd> findAllById(Iterable<String> ids);
}
