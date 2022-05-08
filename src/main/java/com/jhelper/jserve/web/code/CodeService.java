package com.jhelper.jserve.web.code;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;

import java.nio.channels.AlreadyBoundException;
import java.util.Arrays;
import java.util.List;

import com.jhelper.jserve.web.entity.LrgclasCd;
import com.jhelper.jserve.web.entity.SmlclasCd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

    @Autowired
    LargeCodeRepository largeCodeRepository;

    @Autowired
    SmallCodeRepository smallCodeRepository;

    public List<LrgclasCd> getLargeCodes(LrgclasCd largeCode) {

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("lrgclasCd", ignoreCase())
                .withMatcher("lrgclasCdNm", contains())
                .withMatcher("useYn", ignoreCase())
                .withMatcher("cdDv", caseSensitive());

        return largeCodeRepository.findAll(Example.of(largeCode, exampleMatcher));
    }

    public LrgclasCd getLargeCode(String lrgclasCd) {
        return largeCodeRepository.findById(lrgclasCd).orElse(null);
    }

    public List<SmlclasCd> getSmallCodes(String lrgclasCd) {
        return smallCodeRepository.findByLrgclasCd(LrgclasCd.builder().lrgclasCd(lrgclasCd).build());
    }

    public SmlclasCd getSmallCode(String lrgclasCd, String smlclasCd) {
        return smallCodeRepository.findById(
                SmlclasCd.PK.builder()
                        .lrgclasCd(lrgclasCd)
                        .smlclasCd(smlclasCd)
                        .build())
                .get();
    }

    public List<LrgclasCd> getCodes(String... codes) {
        return largeCodeRepository.findAllById(Arrays.asList(codes));
    }

    public LrgclasCd insertLrgclasCd(LrgclasCd newLrgclasCd) {
        LrgclasCd lrgclasCd = getLargeCode(newLrgclasCd.getLrgclasCd());

        if (lrgclasCd != null) {

        }

        lrgclasCd.setLrgclasCdNm(newLrgclasCd.getLrgclasCdNm());
        lrgclasCd.setCdDv(newLrgclasCd.getCdDv());
        lrgclasCd.setUseYn(newLrgclasCd.getUseYn());
        lrgclasCd.setMemo(newLrgclasCd.getMemo());

        return lrgclasCd;
    }

    public LrgclasCd updateLrgclasCd(LrgclasCd newLrgclasCd) {
        LrgclasCd lrgclasCd = getLargeCode(newLrgclasCd.getLrgclasCd());

        lrgclasCd.setLrgclasCdNm(newLrgclasCd.getLrgclasCdNm());
        lrgclasCd.setCdDv(newLrgclasCd.getCdDv());
        lrgclasCd.setUseYn(newLrgclasCd.getUseYn());
        lrgclasCd.setMemo(newLrgclasCd.getMemo());

        return lrgclasCd;
    }
}
