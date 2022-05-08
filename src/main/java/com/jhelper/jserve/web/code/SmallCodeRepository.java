package com.jhelper.jserve.web.code;

import java.util.List;

import com.jhelper.jserve.web.entity.LrgclasCd;
import com.jhelper.jserve.web.entity.SmlclasCd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmallCodeRepository extends JpaRepository<SmlclasCd, SmlclasCd.PK> {
    List<SmlclasCd> findByLrgclasCd(LrgclasCd lrgclasCd);
}
