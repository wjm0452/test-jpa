package com.jhelper.jserve.web.code;

import java.util.List;

import com.jhelper.jserve.web.entity.Lrgclas;
import com.jhelper.jserve.web.entity.Smlclas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmallCodeRepository extends JpaRepository<Smlclas, Smlclas.PK> {
    List<Smlclas> findAllByLrgclasCd(String lrgclasCd);
}
