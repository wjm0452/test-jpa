package com.jhelper.jserve.web.user;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jhelper.jserve.web.entity.Org;
import com.jhelper.jserve.web.entity.User;
import com.jhelper.jserve.web.org.OrgRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Transactional
@SpringBootTest
@Slf4j
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrgRepository orgRepository;

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

        orgCds.stream().forEach(org -> {
            List<User> users = makeUsers(org);
            userRepository.saveAll(users);
        });
    }

    List<User> makeUsers(Org org) {
        String orgCd = org.getOrgCd();
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
                    .gndrDvcd("MALE")
                    .org(org)
                    .build();
        }).collect(Collectors.toList());
    }

    @Test
    void getUsers() {
        List<User> users = userRepository.findAll();
        assertThat(users).isNotNull().size().isGreaterThan(0);

        User user = new User();
        user.setOrg(Org.builder().orgNm("M").useYn("y").build());

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("org.orgNm", contains())
                .withMatcher("org.useYn", ignoreCase());

        users = userRepository.findAll(Example.of(user, exampleMatcher));
        assertThat(users).isNotNull().size().isGreaterThan(0);

        users.forEach(u -> {
            Org org = u.getOrg();
            assertThat(org.getOrgNm()).contains("M");
            assertThat(org.getUseYn()).isEqualTo("Y");
        });
    }

    @Test
    void getUser() {

        User user = userRepository.findById("M_ORG01_USER01").orElse(null);

        assertNotNull(user);
        assertThat(user.getUsrId()).isEqualTo("M_ORG01_USER01");

        assertNotNull(user.getOrg());
    }
}
