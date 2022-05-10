package com.jhelper.jserve.web.board;

import java.util.List;

import com.jhelper.jserve.web.entity.Board;
import com.querydsl.core.Tuple;

public interface BoardRepositoryCustom {
    List<Tuple> findQuery();
    List<Tuple> findQuery2();
}
