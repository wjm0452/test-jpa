package com.jhelper.jserve.web.dto;

import com.jhelper.jserve.web.entity.Smlclas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmallCodeDto {
    private String lrgclasCd;
    private String smlclasCd;
    private String smlclasCdNm;
    private Integer srtSeq;
    private String memo;
    private String useYn;

    public static SmallCodeDto build(Smlclas smlclas) {
        return builder()
                .lrgclasCd(smlclas.getLrgclasCd())
                .smlclasCd(smlclas.getSmlclasCd())
                .smlclasCdNm(smlclas.getSmlclasCdNm())
                .srtSeq(smlclas.getSrtSeq())
                .memo(smlclas.getMemo())
                .useYn(smlclas.getUseYn())
                .build();
    }

    public Smlclas toEntity() {
        return Smlclas.builder()
                .lrgclasCd(lrgclasCd)
                .smlclasCd(smlclasCd)
                .smlclasCdNm(smlclasCdNm)
                .srtSeq(srtSeq)
                .memo(memo)
                .useYn(useYn)
                .build();
    }
}
