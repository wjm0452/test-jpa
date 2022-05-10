package com.jhelper.jserve.web.code;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jhelper.jserve.web.dto.LargeCodeDto;
import com.jhelper.jserve.web.dto.SmallCodeDto;
import com.jhelper.jserve.web.entity.Lrgclas;
import com.jhelper.jserve.web.entity.Smlclas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CodeService {

    @Autowired
    LargeCodeRepository largeCodeRepository;

    @Autowired
    SmallCodeRepository smallCodeRepository;

    @Transactional(readOnly = true)
    public List<LargeCodeDto> getLargeCodes(LargeCodeDto largeCodeDto) {

        Lrgclas lrgclas = largeCodeDto.toEntity();

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("lrgclasCd", ignoreCase())
                .withMatcher("lrgclasCdNm", contains())
                .withMatcher("useYn", ignoreCase())
                .withMatcher("cdDv", caseSensitive());

        return largeCodeRepository.findAll(Example.of(lrgclas, exampleMatcher))
                .stream()
                .map(l -> LargeCodeDto.build(l))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LargeCodeDto getLargeCode(String largeCode) {
        Lrgclas lrgclas = largeCodeRepository.findById(largeCode).orElse(null);

        if (lrgclas == null) {
            return null;
        }

        return LargeCodeDto.build(lrgclas);
    }

    @Transactional(readOnly = true)
    public List<SmallCodeDto> getSmallCodes(String largeCode) {
        return smallCodeRepository.findAllByLrgclasCd(largeCode).stream().map(lrgclas -> {
            return SmallCodeDto.build(lrgclas);
        }).collect(Collectors.toList());
    }

    public SmallCodeDto getSmallCode(String lrgclasCd, String smlclasCd) {
        Smlclas smlclas = smallCodeRepository.findById(new Smlclas.PK(lrgclasCd, smlclasCd)).orElse(null);

        if (smlclas == null) {
            return null;
        }

        return SmallCodeDto.build(smlclas);
    }

    @Transactional
    public LargeCodeDto setLargeCode(LargeCodeDto largeCodeDto) {
        return LargeCodeDto.build(largeCodeRepository.save(largeCodeDto.toEntity()));
    }

    @Transactional
    public SmallCodeDto setSmallCode(SmallCodeDto smallCodeDto) {
        return SmallCodeDto.build(smallCodeRepository.save(smallCodeDto.toEntity()));
    }

    @Transactional
    public void deleteLargeCode(String largeCode) {
        largeCodeRepository.deleteById(largeCode);
    }

    @Transactional
    public void deleteSmallCode(String largeCode, String smallCode) {
        smallCodeRepository.deleteById(new Smlclas.PK(largeCode, smallCode));
    }

    @Transactional(readOnly = true)
    public List<LargeCodeDto> getCodes(String... codes) {
        return largeCodeRepository.findAllById(Arrays.asList(codes)).stream().map(lrgclas -> {
            LargeCodeDto largeCodeDto = LargeCodeDto.build(lrgclas);
            List<SmallCodeDto> smallCodeDtos = lrgclas.getSmlclass()
                    .stream()
                    .map(smlclas -> SmallCodeDto.build(smlclas))
                    .collect(Collectors.toList());

            largeCodeDto.setSmallCodes(smallCodeDtos);

            return largeCodeDto;
        }).collect(Collectors.toList());
    }
}
