package com.jhelper.jserve.web.board;

import java.util.List;

import com.jhelper.jserve.web.dto.BoardAdmin2Dto;
import com.jhelper.jserve.web.dto.BoardAdminDto;
import com.jhelper.jserve.web.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, String>, BoardRepositoryCustom {

    @Query("select b.blbdId, b.blbdNm " +
            "from Board b")
    List<String> findAllBoardIds();

    @Query("select count(*) " +
            "from Board b " +
            "where b.blbdId = :blbdId")
    int getBoardCount(@Param("blbdId") String blbdId);

    @Query("select new com.jhelper.jserve.web.dto.BoardAdminDto(b.blbdId, b.blbdNm, ba.usrId, ba.useYn) " +
            "from Board b " +
            "left join b.boardAdmins ba " +
            "where b.blbdId = :blbdId")
    List<BoardAdminDto> findBoardAdmin(@Param("blbdId") String blbdId);

    @Query("select b.blbdId as blbdId, b.blbdNm as blbdNm, ba.usrId as usrId, ba.useYn as useYn " +
            "from Board b " +
            "left join b.boardAdmins ba " +
            "where b.blbdId = :blbdId")
    <T> List<T> findBoardAdmin2(@Param("blbdId") String blbdId, Class<T> type);
}
