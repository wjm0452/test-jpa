package com.jhelper.jserve.web.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TBSYSZ004C")
@IdClass(SmlclasCdId.class)
public class SmlclasCd {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lrgclas_cd", referencedColumnName = "lrgclasCd")
    private LrgclasCd lrgclasCd;

    @Id
    private String smlclasCd;
    private String smlclasCdNm;
    private String srtSeq;
    private String memo;
    private String useYn;
    private String regId;
    private String regDtm;
    private String regPgmId;
    private String lstUpdId;
    private String lstUpdDtm;
    private String lstUpdPgmId;
}
