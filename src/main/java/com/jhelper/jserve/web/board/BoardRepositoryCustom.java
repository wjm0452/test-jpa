package com.jhelper.jserve.web.board;

import java.util.List;

import com.jhelper.jserve.web.entity.Board;

public interface BoardRepositoryCustom {
    List<Board> findByTitle(String title);
}
