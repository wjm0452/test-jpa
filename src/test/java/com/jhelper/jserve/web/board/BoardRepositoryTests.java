package com.jhelper.jserve.web.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.jhelper.jserve.web.dto.BoardAdmin2Dto;
import com.jhelper.jserve.web.dto.BoardAdminDto;
import com.jhelper.jserve.web.entity.Board;
import com.jhelper.jserve.web.entity.BoardAdmin;
import com.querydsl.core.Tuple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

// @Transactional
@SpringBootTest
@Slf4j
public class BoardRepositoryTests {

    @Autowired
    BoardRepository boardRepository;

    // @BeforeEach
    void before() {

        Board board = Board.builder()
                .blbdId("M_TEST01")
                .blbdDvcd("T01")
                .blbdNm("테스트입니다.")
                .srtSeq(1)
                .blbdDesc("게시판입니다.")
                .blbdUseYn("Y")
                .allOrgRlseYn("N")
                .blbdRlseYn("N")
                .blthgAlrmUseYn("Y")
                .pfcUseYn("Y")
                .clsdDispYn("Y")
                .replyPmssYn("Y")
                .boardAdmins(makeAdmin("M_TEST01"))
                .build();

        boardRepository.save(board);

        board = Board.builder()
                .blbdId("M_TEST02")
                .blbdDvcd("T01")
                .blbdNm("테스트입니다.")
                .srtSeq(1)
                .blbdDesc("게시판입니다.")
                .blbdUseYn("Y")
                .allOrgRlseYn("N")
                .blbdRlseYn("N")
                .blthgAlrmUseYn("Y")
                .pfcUseYn("Y")
                .clsdDispYn("Y")
                .replyPmssYn("Y")
                .boardAdmins(makeAdmin("M_TEST02"))
                .build();

        boardRepository.save(board);
    }

    List<BoardAdmin> makeAdmin(String blbdId) {
        List<BoardAdmin> resultList = new ArrayList<>();
        resultList.add(BoardAdmin.builder().blbdId(blbdId).usrId("ADMIN").useYn("Y").build());
        resultList.add(BoardAdmin.builder().blbdId(blbdId).usrId("SADMIN").useYn("Y").build());

        return resultList;
    }

    @DisplayName("getAll")
    @Test
    void getAll() {
        List<Board> boards = boardRepository.findAll();
        assertThat(boards).isNotNull().size().isGreaterThan(0);

        assertThat(boards.get(0).getBoardAdmins()).hasSize(2);
        assertThat(boards.get(1).getBoardAdmins()).hasSize(2);
    }

    @DisplayName("boardIds")
    @Test
    void getBlbdIds() {
        List<String> boardIds = boardRepository.findAllBoardIds();
        assertThat(boardIds).isNotNull().size().isGreaterThan(0);
    }

    @DisplayName("count")
    @Test
    void getBoardCount() {
        int count = boardRepository.getBoardCount("M_TEST01");
        assertThat(count).isEqualTo(1);
    }

    @DisplayName("getBoardAdmin")
    @Test
    void getBoardAdmin() {
        List<BoardAdminDto> boardAdmins = boardRepository.findBoardAdmin("M_TEST01");
        assertThat(boardAdmins).hasSize(2);

        assertThat(boardAdmins.get(0).getUseYn()).isEqualTo("Y");
    }

    @DisplayName("getBoardAdmin2")
    @Test
    void getBoardAdmin2() {
        List<BoardAdmin2Dto> boardAdmins = boardRepository.findBoardAdmin2("M_TEST01", BoardAdmin2Dto.class);
        assertThat(boardAdmins).hasSize(2);

        assertThat(boardAdmins.get(0).getBlbdId()).isNotNull();
    }

    @DisplayName("queryDsl")
    @Test
    void queryDsl() {
        List<Tuple> boardAdmins = boardRepository.findQuery();
        assertThat(boardAdmins).hasSize(4);
    }

    @DisplayName("queryDsl2")
    @Test
    void queryDsl2() {
        List<Tuple> boardAdmins = boardRepository.findQuery2();
        assertThat(boardAdmins).hasSize(2);
    }
}
