package com.jhelper.jserve.web.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    BoardRepository boardRepository;

    @Test
    void board() {
        assertThat(
                boardRepository.findByTitle("title"))
                .size()
                .isEqualTo(0);
    }
}
