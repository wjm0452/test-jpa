package com.jhelper.jserve.web.org;

import com.jhelper.jserve.web.entity.OrgHistory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgHistoryRepository extends JpaRepository<OrgHistory, OrgHistory.PK>, OrgHistoryRepositoryCustom {

}
