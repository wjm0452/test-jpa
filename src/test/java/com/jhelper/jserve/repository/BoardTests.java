package com.jhelper.jserve.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import com.jhelper.jserve.web.entity.User;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardTests {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    EntityManager entityManager;

    @Test
    void board() {
        assertThat(entityManager).isNotNull();

        List<User> users = entityManager.createQuery("select u from User u", User.class).getResultList();

        assertThat(users).size().isGreaterThan(0);
    }

    @Test
    void board2() {

        PathBuilderFactory pathBuilderFactory = new PathBuilderFactory();
        PathBuilder<User> user = pathBuilderFactory.create(User.class);

        BooleanExpression idExpr = user.get("usrId").eq("SADMIN");
        BooleanExpression nameExpr = user.get("usrNm").eq("고도화 2관리자");
        BooleanExpression useExpr = user.get("useYn").eq("Y");

        assertThat(
                jpaQueryFactory
                        .selectFrom(user)
                        .rightJoin(user.get("org"))
                        .fetchJoin()
                        .where(
                                idExpr.or(nameExpr),
                                useExpr)
                        .fetch())
                .size()
                .isEqualTo(1);
    }
}
