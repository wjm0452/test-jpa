package com.jhelper.jserve.web.org;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import com.jhelper.jserve.web.entity.OrgHistory;
import com.querydsl.core.Tuple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Transactional
@SpringBootTest
@Slf4j
public class OrgHistoryRepositoryTests {

    @Autowired
    OrgHistoryRepository orgHistoryRepository;

    @Test
    void queryDsl() {
        OrgHistory orgHistory = new OrgHistory();
        orgHistory.setOrgCd("");
        List<OrgHistory> histories = orgHistoryRepository.findAll(orgHistory);

        assertThat(histories).isNotNull().hasSize(0);
    }

    @Test
    void queryDsl2() {
        OrgHistory orgHistory = new OrgHistory();
        orgHistory.setOrgCd("11");
        orgHistory.setOrgNm("SEIZE");
        List<Tuple> histories = orgHistoryRepository.findAll2(orgHistory);

        assertThat(histories).isNotNull().size().isGreaterThan(0);
    }

    @Test
    void queryDsl3() {

        List<Tuple> histories = orgHistoryRepository.findOrgCdAndChangedCount();

        assertThat(histories).isNotNull().size().isGreaterThan(0);
    }
}
