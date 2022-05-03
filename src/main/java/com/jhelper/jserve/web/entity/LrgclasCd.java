package com.jhelper.jserve.web.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "TBSYSZ003C")
public class LrgclasCd {
    @Id
    private String lrgclasCd;
    private String lrgclasCdNm;
    private String cdDv;
    private String srtSeq;
    private String memo;
    private String useYn;
    private String regId;
    private String regDtm;
    private String regPgmId;
    private String lstUpdId;
    private String lstUpdDtm;
    private String lstUpdPgmId;

    @OneToMany(mappedBy = "lrgclasCd", fetch = FetchType.LAZY)
    private List<SmlclasCd> smlclasCds;
}
