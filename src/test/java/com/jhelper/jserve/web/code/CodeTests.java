package com.jhelper.jserve.web.code;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.jhelper.jserve.web.entity.LrgclasCd;
import com.jhelper.jserve.web.entity.SmlclasCd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CodeTests {

    @Autowired
    CodeService codeService;

    @Test
    void largeCodes() {

        assertThat(codeService.findLargeCodes()).isNotEmpty();

        // ignore case
        LrgclasCd largeCode = LrgclasCd.builder().lrgclasCd("org_KIND_DVCD").build();
        assertThat(codeService.findLargeCodes(largeCode)).size().isEqualTo(1);

        // not contain
        largeCode = LrgclasCd.builder().lrgclasCdNm("실제없어야 테이블에 합니다.").build();
        assertThat(codeService.findLargeCodes(largeCode)).size().isEqualTo(0);

        // not contain
        largeCode = LrgclasCd.builder().lrgclasCd("ORG_KIND_DVCD").lrgclasCdNm("실제없어야 테이블에 합니다.").build();
        assertThat(codeService.findLargeCodes(largeCode)).size().isEqualTo(0);

        // like
        largeCode = LrgclasCd.builder().lrgclasCd("ORG_KIND_DVCD").lrgclasCdNm("조직").build();
        assertThat(codeService.findLargeCodes(largeCode)).size().isEqualTo(1);

        // other condition
        largeCode = LrgclasCd.builder().cdDv("SYS").build();
        assertThat(codeService.findLargeCodes(largeCode)).size().isGreaterThan(0);

        // other condition
        largeCode = LrgclasCd.builder().cdDv("ABCEFWF").build();
        assertThat(codeService.findLargeCodes(largeCode)).size().isEqualTo(0);
    }

    @Test
    void largeCode() {
        assertThat(codeService.findLargeCode("ORG_KIND_DVCD").getLrgclasCd()).isEqualTo("ORG_KIND_DVCD");
        assertThat(codeService.findLargeCode("ORG_KIND_DVCD2")).isNull();
    }

    @Test
    void smallCodes() {
        // large code
        assertThat(codeService.findSmallCodes("ORG_KIND_DVCD")).size().isGreaterThan(0);

        // large code
        assertThat(codeService.findSmallCodes("ORG_KIND_DVCD2")).size().isEqualTo(0);
    }

    @Test
    void smallCode() {
        // small code
        assertThat(codeService.findSmallCode("ORG_KIND_DVCD", "CENTER")).isNotNull();

        // small code
        assertThat(codeService.findSmallCode("ORG_KIND_DVCD", "CENTERABCD")).isNull();
    }

    @Transactional(readOnly = true)
    @Test
    void largeAndSmallCode() {

        LrgclasCd lrgclasCd = codeService.findLargeCode("ORG_KIND_DVCD");

        List<SmlclasCd> smlclasCds = lrgclasCd.getSmlclasCds();

        assertThat(smlclasCds).size().isGreaterThan(0);
    }

    @Test
    void multiCodes() {
        List<LrgclasCd> lrgclasCds = codeService.findCodes("ORG_KIND_DVCD", "MSG_CD");
        lrgclasCds.stream().forEach(lrgclasCd -> assertThat(lrgclasCd.getSmlclasCds()).size().isGreaterThan(0));
    }
}
