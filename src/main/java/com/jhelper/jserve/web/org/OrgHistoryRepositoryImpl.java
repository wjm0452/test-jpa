package com.jhelper.jserve.web.org;

import java.util.List;

import javax.persistence.EntityManager;

import com.jhelper.jserve.web.entity.Org;
import com.jhelper.jserve.web.entity.OrgHistory;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;

import org.springframework.beans.factory.annotation.Autowired;

public class OrgHistoryRepositoryImpl implements OrgHistoryRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    public List<OrgHistory> findAll(OrgHistory orgHistory) {

        PathBuilder<OrgHistory> oh = new PathBuilder<>(OrgHistory.class, "oh");

        JPAQuery<OrgHistory> query = new JPAQuery<>(entityManager);

        return query.select(oh)
                .from(oh)
                .where(oh.getString("orgCd").isNotNull(),
                        oh.getString("orgCd").eq(orgHistory.getOrgCd()))
                .fetch();
    }

    public List<Tuple> findAll2(OrgHistory orgHistory) {

        PathBuilder<OrgHistory> oh = new PathBuilder<>(OrgHistory.class, "oh");
        PathBuilder<Org> o = new PathBuilder<>(Org.class, "o");

        JPAQuery<Tuple> query = new JPAQuery<>(entityManager);

        return query
                .select(
                        oh.getString("orgCd"),
                        oh.getString("orgNm"))
                .from(oh)
                .join(o)
                .on(oh.getString("orgCd").eq(o.getString("orgCd")))
                .where(oh.getString("orgCd").isNotNull(),
                        o.getString("orgCd").eq(orgHistory.getOrgCd())
                                .or(o.getString("orgNm").like("%" + orgHistory.getOrgNm() + "%")))
                .fetch();
    }

    public List<Tuple> findOrgCdAndChangedCount() {

        PathBuilder<Org> o = new PathBuilder<>(Org.class, "o");
        PathBuilder<OrgHistory> oh = new PathBuilder<>(OrgHistory.class, "oh");

        JPAQuery<Tuple> query = new JPAQuery<>(entityManager);

        return query
                .select(o.getString("orgCd"),
                        oh.count().as("changedCount"))
                .from(o)
                .leftJoin(oh)
                .on(o.getString("orgCd").eq(oh.getString("orgCd")))
                .groupBy(o.getString("orgCd"))
                .fetch();
    }

    // JPAExpressions - inline view 사용시
}
