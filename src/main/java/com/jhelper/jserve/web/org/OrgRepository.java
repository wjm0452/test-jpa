package com.jhelper.jserve.web.org;

import java.util.List;
import java.util.Optional;

import com.jhelper.jserve.web.entity.Org;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepository extends JpaRepository<Org, String> {

    @EntityGraph(attributePaths = { "users" })
    @Query("select o " +
            "from Org o " +
            "where o.orgCd = :orgCd")
    List<Org> findAllWithUsers(@Param("orgCd") String orgCd);

    @Query("select distinct o " +
            "from Org o " +
            "join fetch o.users " +
            "where o.orgCd = :#{#org.orgCd}")
    List<Org> findAllWithUsers(@Param("org") Org org);

    @EntityGraph(attributePaths = { "users" })
    @Override
    Optional<Org> findById(String id);
}
