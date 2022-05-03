package com.jhelper.jserve.web.code;

import java.util.Arrays;
import java.util.List;

import com.jhelper.jserve.web.entity.LrgclasCd;
import com.jhelper.jserve.web.entity.SmlclasCd;
import com.jhelper.jserve.web.entity.SmlclasCdId;

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

    public List<LrgclasCd> findLargeCodes() {
        return largeCodeRepository.findAll();
    }

    public LrgclasCd findLargeCode(String lrgclasCd) {
        return largeCodeRepository.findById(lrgclasCd).orElse(null);
    }

    public List<LrgclasCd> findLargeCodes(LrgclasCd largeCode) {

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("lrgclasCd", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
                .withMatcher("lrgclasCdNm", ExampleMatcher.GenericPropertyMatchers.contains());
        // largeCodeRepository.findAll(Example.of(largeCode));
        return largeCodeRepository.findAll(Example.of(largeCode, exampleMatcher));
    }

    public List<SmlclasCd> findSmallCodes(String lrgclasCd) {
        return smallCodeRepository.findAll(
                Example.of(
                        SmlclasCd.builder()
                                .lrgclasCd(
                                        LrgclasCd.builder()
                                                .lrgclasCd(lrgclasCd)
                                                .build())
                                .build()));
    }

    public SmlclasCd findSmallCode(String lrgclasCd, String smlclasCd) {
        SmlclasCdId smallCodeID = SmlclasCdId.builder().lrgclasCd(lrgclasCd).smlclasCd(smlclasCd).build();
        return smallCodeRepository.findById(smallCodeID).orElse(null);
    }

    public List<LrgclasCd> findCodes(String... lrgclasCds) {
        return largeCodeRepository.findAllById(Arrays.asList(lrgclasCds));
    }
}
