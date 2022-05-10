package com.jhelper.jserve.web.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import com.jhelper.jserve.web.dto.LargeCodeDto;
import com.jhelper.jserve.web.dto.SmallCodeDto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CodeServiceTests {

    @Autowired
    CodeService codeService;

    @BeforeEach
    void beforeCode() {
        saveLargeCode();
        saveSmallCode();
    }

    @DisplayName("save large code")
    void saveLargeCode() {

        String largeCode = "M_TEST01";
        String largeName = "M테스트01";

        codeService.setLargeCode(
                LargeCodeDto.builder()
                        .lrgclasCd(largeCode)
                        .lrgclasCdNm(largeName)
                        .srtSeq(1)
                        .cdDv("SYS")
                        .memo("대분류코드")
                        .build());

        largeCode = "M_TEST02";
        largeName = "M테스트02";

        codeService.setLargeCode(
                LargeCodeDto.builder()
                        .lrgclasCd(largeCode)
                        .lrgclasCdNm(largeName)
                        .srtSeq(1)
                        .cdDv("SYS")
                        .memo("대분류코드")
                        .build());

    }

    @DisplayName("save small code")
    void saveSmallCode() {

        String largeCode = "M_TEST01";
        String smallCode = "M_TEST01_S01";
        String smallName = "M_S테스트01";

        codeService.setSmallCode(
                SmallCodeDto.builder()
                        .lrgclasCd(largeCode)
                        .smlclasCd(smallCode)
                        .smlclasCdNm(smallName)
                        .srtSeq(1)
                        .memo("소분류코드")
                        .build());

        largeCode = "M_TEST01";
        smallCode = "M_TEST01_S02";
        smallName = "M_S테스트02";

        codeService.setSmallCode(
                SmallCodeDto.builder()
                        .lrgclasCd(largeCode)
                        .smlclasCd(smallCode)
                        .smlclasCdNm(smallName)
                        .srtSeq(1)
                        .memo("소분류코드")
                        .build());

    }

    @Test
    @DisplayName("get large code")
    void getLargeCode() {

        final String largeCode = "M_TEST01";
        final String largeName = "M테스트01";

        LargeCodeDto code = codeService.getLargeCode(largeCode);

        assertThat(code.getLrgclasCd()).isEqualTo(largeCode);
        assertThat(code.getLrgclasCdNm()).isEqualTo(largeName);

        LargeCodeDto largeCodeDto = LargeCodeDto.builder().lrgclasCdNm("M테스트").build();
        List<LargeCodeDto> largeCodeDtos = codeService.getLargeCodes(largeCodeDto);

        assertThat(largeCodeDtos).hasSize(2);
    }

    @Test
    @DisplayName("get small code")
    void getSmallCode() {

        final String largeCode = "M_TEST01";
        final String smallCode = "M_TEST01_S01";
        final String smallName = "M_S테스트01";

        SmallCodeDto code = codeService.getSmallCode(largeCode, smallCode);

        assertThat(code.getLrgclasCd()).isEqualTo(largeCode);
        assertThat(code.getSmlclasCd()).isEqualTo(smallCode);
        assertThat(code.getSmlclasCdNm()).isEqualTo(smallName);

        List<SmallCodeDto> smallCodeDtos = codeService.getSmallCodes(largeCode);

        assertThat(smallCodeDtos).hasSize(2);
    }

    @Test
    @DisplayName("get large code")
    void deleteLargeCode() {
        final String largeCode = "M_TEST01";

        codeService.deleteLargeCode(largeCode);

        LargeCodeDto code = codeService.getLargeCode(largeCode);

        assertNull(code);
    }

    @Test
    @DisplayName("get small code")
    void deleteSmallCode() {
        final String largeCode = "M_TEST01";
        final String smallCode = "M_TEST01_S01";

        codeService.deleteSmallCode(largeCode, smallCode);

        SmallCodeDto code = codeService.getSmallCode(largeCode, smallCode);

        assertNull(code);
    }

    @Test
    @DisplayName("get codes")
    void getCodes() {
        final String largeCode = "M_TEST01";

        List<LargeCodeDto> largeCodeDtos = codeService.getCodes(largeCode);

        assertThat(largeCodeDtos).hasSize(1);

        List<SmallCodeDto> smallCodeDtos = largeCodeDtos.get(0).getSmallCodes();
        assertThat(smallCodeDtos)
                .isNotNull()
                .hasSize(2);
    }

}
