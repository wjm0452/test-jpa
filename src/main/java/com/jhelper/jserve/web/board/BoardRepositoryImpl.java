package com.jhelper.jserve.web.board;

import java.util.List;

import com.jhelper.jserve.web.entity.Board;
import com.jhelper.jserve.web.entity.BoardAdmin;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Operator;
import com.querydsl.core.types.PredicateOperation;
import com.querydsl.core.types.PredicateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {

    JPAQueryFactory jpaQueryFactory;

    public BoardRepositoryImpl() {
        super(Board.class);
    }

    public List<Tuple> findQuery() {

        PathBuilder<Board> board = getBuilder();

        List<Tuple> results = getQuerydsl().createQuery()
                .select(board.getString("blbdId"), board.getString("blbdNm"))
                .from(board)
                .innerJoin(board.get("boardAdmins"))
                .fetch();

        return results;
    }

    public List<Tuple> findQuery2() {

        PathBuilder<Board> board = getBuilder();
        PathBuilder<BoardAdmin> boardAdmin = getBuilder();

        List<Tuple> results = getQuerydsl().createQuery()
                .select(boardAdmin.get("useYn"), boardAdmin.get("usrId"))
                .from(board)
                .leftJoin(boardAdmin)
                .on(board.get("blbdId").eq(boardAdmin.get("blbdId")))
                .fetch();

        return results;
    }
}
