package com.jhelper.jserve.web.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "TBCOM2010M")
public class Board implements Serializable {
    @Id
    private String blbdId;
    private String blbdDvcd;
    private String blbdNm;
    private int srtSeq;
    private String blbdDesc;
    private String blbdUseYn;
    private String allOrgRlseYn;
    private String blbdRlseYn;
    private String blthgAlrmUseYn;;
    private String pfcUseYn;
    private String clsdDispYn;
    private String replyPmssYn;
    private String regId;
    private String regDtm;
    private String regPgmId;
    private String lstUpdId;
    private String lstUpdDtm;
    private String lstUpdPgmId;
}
