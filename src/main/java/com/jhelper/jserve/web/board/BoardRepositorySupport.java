package com.jhelper.jserve.web.board;

import com.jhelper.jserve.web.entity.Board;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BoardRepositorySupport extends QuerydslRepositorySupport {

    JPAQueryFactory jpaQueryFactory;
    public BoardRepositorySupport() {
        super(Board.class);
    }

    public void findAll() {

    }
}
