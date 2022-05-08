package com.jhelper.jserve.web.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import com.jhelper.jserve.web.entity.LrgclasCd;
import com.jhelper.jserve.web.entity.SmlclasCd;

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
public class CodeRepositoryTests {

    @Autowired
    LargeCodeRepository largeCodeRepository;

    @Autowired
    SmallCodeRepository smallCodeRepository;

    @BeforeEach
    @DisplayName("before code")
    @Transactional
    void beforeCode() {
        log.info("before code");
        String[] largeCodes = { "WWW01", "WWW02" };

        List<LrgclasCd> lrgclasCds = Arrays.stream(largeCodes).map(largeCode -> {
            String largeName = largeCode + "_NM";
            return LrgclasCd.builder()
                    .lrgclasCd(largeCode).lrgclasCdNm(largeName).cdDv("SYS").srtSeq(1).useYn("Y")
                    .build();
        }).toList();

        lrgclasCds = largeCodeRepository.saveAll(lrgclasCds);

        lrgclasCds.forEach(lrgclasCd -> {
            lrgclasCd.setSmlclasCds(makeSmallCodes(lrgclasCd));
        });
    }

    List<SmlclasCd> makeSmallCodes(LrgclasCd lrgclasCd) {

        final String largeCode = lrgclasCd.getLrgclasCd();
        String[] smallCodes = { largeCode + "_S_01", largeCode + "_S_02" };

        return Arrays.stream(smallCodes).map(smallCode -> {
            String smallName = smallCode + "_NM";
            return SmlclasCd.builder()
                    .lrgclasCd(LrgclasCd.builder().lrgclasCd(largeCode).build())
                    .smlclasCd(smallCode).smlclasCdNm(smallName).srtSeq(1).useYn("Y")
                    .build();
        }).toList();
    }

    @Test
    @DisplayName("large code test")
    @Transactional
    void largeCodeTest() {

        log.info("find large code");
        checkEntity(largeCodeRepository.findById("WWW01").orElse(null));

        log.info("find large codes");
        largeCodeRepository.findAllById(Arrays.asList(new String[] { "WWW01", "WWW02"
        })).stream()
                .forEach((lrgclasCd) -> checkEntity(lrgclasCd));

        log.info("find large code in small");
        assertThat(largeCodeRepository.findById("WWW01").get().getSmlclasCds()).hasSize(2);

        log.info("delete large code");
        largeCodeRepository.deleteById("WWW02");
        assertNull(largeCodeRepository.findById("WWW02").orElse(null));
    }

    @Test
    @DisplayName("small code test")
    @Transactional
    void smallCodeTest() {

        log.info("find small code");
        checkEntity(smallCodeRepository
                .findById(SmlclasCd.PK.builder().lrgclasCd("ORG_KIND_DVCD").smlclasCd("CENTER").build())
                .get());

        log.info("find small codes");
        smallCodeRepository.findByLrgclasCd(LrgclasCd.builder().lrgclasCd("WWW01").build())
                .forEach((smlclasCd) -> checkEntity(smlclasCd));
    }

    void checkEntity(SmlclasCd smlclasCd) {
        log.info("check entity: {}", smlclasCd.toString());

        assertThat(smlclasCd).isNotNull();

        assertNotNull(smlclasCd.getLrgclasCd().toString());
        assertNotNull(smlclasCd.getSrtSeq());
        assertNotNull(smlclasCd.getRegId());
        assertNotNull(smlclasCd.getRegDtm());
        assertNotNull(smlclasCd.getRegPgmId());
        assertNotNull(smlclasCd.getLstUpdId());
        assertNotNull(smlclasCd.getLstUpdDtm());
        assertNotNull(smlclasCd.getLstUpdPgmId());
    }

    void checkEntity(LrgclasCd lrgclasCd) {
        log.info("check entity: {}", lrgclasCd.toString());

        assertThat(lrgclasCd).isNotNull();

        assertNotNull(lrgclasCd.getLrgclasCd());
        assertNotNull(lrgclasCd.getLrgclasCdNm());
        assertNotNull(lrgclasCd.getCdDv());
        assertNotNull(lrgclasCd.getSrtSeq());
        assertNotNull(lrgclasCd.getRegId());
        assertNotNull(lrgclasCd.getRegDtm());
        assertNotNull(lrgclasCd.getRegPgmId());
        assertNotNull(lrgclasCd.getLstUpdId());
        assertNotNull(lrgclasCd.getLstUpdDtm());
        assertNotNull(lrgclasCd.getLstUpdPgmId());
    }

}
