package com.jhelper.jserve.web.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import com.jhelper.jserve.web.entity.Board;
import com.jhelper.jserve.web.entity.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;

@SpringBootTest
public class BoardTests {

    @Autowired
    EntityManager entityManager;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    void boards() {
        jpaQueryFactory.from(QBoard.board);

    }

}
