package com.jhelper.jserve.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.jhelper.jserve.web.entity.Lrgclas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LargeCodeDto {
    private String lrgclasCd;
    private String lrgclasCdNm;
    private String cdDv;
    private Integer srtSeq;
    private String memo;
    private String useYn;
    private List<SmallCodeDto> smallCodes;

    public static LargeCodeDto build(Lrgclas lrgclas) {
        return builder()
                .lrgclasCd(lrgclas.getLrgclasCd())
                .lrgclasCdNm(lrgclas.getLrgclasCdNm())
                .cdDv(lrgclas.getCdDv())
                .srtSeq(lrgclas.getSrtSeq())
                .memo(lrgclas.getMemo())
                .useYn(lrgclas.getUseYn())
                .build();
    }

    public Lrgclas toEntity() {
        return Lrgclas.builder()
                .lrgclasCd(lrgclasCd)
                .lrgclasCdNm(lrgclasCdNm)
                .srtSeq(srtSeq)
                .memo(memo)
                .useYn(useYn)
                .smlclass(new ArrayList<>())
                .build();
    }
}
