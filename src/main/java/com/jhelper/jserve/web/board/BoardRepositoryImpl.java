package com.jhelper.jserve.web.board;

import java.util.List;

import com.jhelper.jserve.web.entity.Board;
import com.jhelper.jserve.web.entity.BoardAdmin;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Operator;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.PredicateOperation;
import com.querydsl.core.types.PredicateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.SimplePath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.querydsl.QuerydslUtils;

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

        // PathBuilderFactory pbf = new PathBuilderFactory();
        // PathBuilder<Board> board = getBuilder();
        // PathBuilder<BoardAdmin> boardAdmin = pbf.create(BoardAdmin.class);

        PathBuilder<Board> board = new PathBuilder<>(Board.class, "b");
        PathBuilder<BoardAdmin> boardAdmin = new PathBuilder<>(BoardAdmin.class, "ba");

        List<Tuple> results = getQuerydsl().createQuery()
                .select(board.getString("blbdId"),
                        board.getString("blbdNm"),
                        boardAdmin.getString("usrId"),
                        boardAdmin.getString("useYn"))
                .from(board)
                .leftJoin(boardAdmin)
                .on(board.get("blbdId").eq(boardAdmin.get("blbdId")))
                .orderBy(new OrderSpecifier<String>(Order.ASC, board.getString("blbdId")))
                .fetch();

        return results;
    }
}
