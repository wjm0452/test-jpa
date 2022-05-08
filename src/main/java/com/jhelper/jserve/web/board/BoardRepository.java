package com.jhelper.jserve.web.board;

import com.jhelper.jserve.web.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, String>, BoardRepositoryCustom {

}
