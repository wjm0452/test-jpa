package com.jhelper.jserve.web.board;

import java.util.List;

import com.jhelper.jserve.web.entity.Board;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

    JPAQueryFactory jpaQueryFactory;

    public BoardRepositoryImpl() {
        super(Board.class);
    }

    public List<Board> findByTitle(String title) {

        PathBuilder<Board> board = getBuilder();

        return from(board)
                .where(
                        board.getString("blbdNm").like(title))
                .fetch();
    }
}
