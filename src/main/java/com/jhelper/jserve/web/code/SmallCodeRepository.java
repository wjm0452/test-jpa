package com.jhelper.jserve.web.code;

import com.jhelper.jserve.web.entity.SmlclasCd;
import com.jhelper.jserve.web.entity.SmlclasCdId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmallCodeRepository extends JpaRepository<SmlclasCd, SmlclasCdId> {

}
