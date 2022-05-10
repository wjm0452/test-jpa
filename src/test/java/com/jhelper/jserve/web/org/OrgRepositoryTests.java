package com.jhelper.jserve.web.org;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jhelper.jserve.web.entity.Org;
import com.jhelper.jserve.web.entity.User;
import com.jhelper.jserve.web.user.UserRepository;

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
public class OrgRepositoryTests {

    @Autowired
    OrgRepository orgRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    @DisplayName("before")
    void beforeCode() {
        log.info("before code");
        String[] orgCodes = { "M_ORG01", "M_ORG02" };

        List<Org> orgCds = Arrays.stream(orgCodes).map(orgCode -> {
            String orgName = orgCode + "_NM";
            return Org.builder()
                    .orgCd(orgCode)
                    .orgNm(orgName)
                    .orgLvl(1)
                    .prtsOrgCd("10")
                    .bizDvcd("NN")
                    .orgKindDvcd("COUNSEL")
                    .srtSeq(1)
                    .useYn("Y")
                    .build();

        }).collect(Collectors.toList());

        orgCds = orgRepository.saveAll(orgCds);
    }

    List<User> makeUsers(String orgCd) {

        String[] userIds = { orgCd + "_USER01", orgCd + "USER02" };

        return Arrays.stream(userIds).map(userId -> {
            return User.builder().usrId(userId)
                    .pw("")
                    .useYn("Y")
                    .usrRoleId("A220418010")
                    .orgCd("M_ORG01")
                    .lginYn("N")
                    .lginTryTcnt(0)
                    .pwUpdDtm("")
                    .usrNm("SEIZE")
                    .mbphNo("01011112222")
                    .emlAddr("seize@buttle.co.kr")
                    .gndrDvcd("MALE").build();
        }).collect(Collectors.toList());
    }

    @Test
    void getOrg() {

        Org org = orgRepository.findById("M_ORG01").orElseGet(null);

        assertNotNull(org);
        assertThat(org.getOrgCd()).isEqualTo("M_ORG01");
        assertThat(org.getOrgKindDvcd()).isEqualTo("COUNSEL");
    }

    @Test
    void findAllById() {

        List<Org> orgs = orgRepository.findAllById(Arrays.asList("M_ORG01", "M_ORG02"));

        assertThat(orgs).isNotNull().hasSize(2);
    }
}
