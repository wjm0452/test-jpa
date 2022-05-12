package com.jhelper.jserve.web.code;

import java.util.List;
import java.util.Optional;

import com.jhelper.jserve.web.entity.Smlclas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmallCodeRepository extends JpaRepository<Smlclas, Smlclas.PK> {
    List<Smlclas> findAllByLrgclasCd(String lrgclasCd);
    Optional<Smlclas> findByLrgclasCdAndSmlclasCd(String lrgclasCd, String smlclasCd);
}
