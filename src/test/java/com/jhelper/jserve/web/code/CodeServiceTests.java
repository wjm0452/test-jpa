package com.jhelper.jserve.web.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.jhelper.jserve.web.entity.LrgclasCd;
import com.jhelper.jserve.web.entity.SmlclasCd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class CodeServiceTests {

    @Autowired
    CodeService codeService;

    @BeforeEach
    @DisplayName("before code")
    @Transactional
    void beforeCode() {

    }

    void codeTest() {

    }

}
