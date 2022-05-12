package com.jhelper.jserve.web.org;

import java.util.List;

import com.jhelper.jserve.web.entity.OrgHistory;
import com.querydsl.core.Tuple;

public interface OrgHistoryRepositoryCustom {
    public List<OrgHistory> findAll(OrgHistory orgHistory);

    public List<Tuple> findAll2(OrgHistory orgHistory);

    public List<Tuple> findOrgCdAndChangedCount();
}
