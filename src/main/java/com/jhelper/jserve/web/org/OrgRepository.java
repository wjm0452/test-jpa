package com.jhelper.jserve.web.org;

import java.util.List;
import java.util.Optional;

import com.jhelper.jserve.web.entity.Org;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepository extends JpaRepository<Org, String> {

}
