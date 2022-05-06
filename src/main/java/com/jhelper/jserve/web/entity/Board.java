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
@Table(name = "TBCOM2010M")
public class Board extends BaseEntity {
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

    @OneToMany(mappedBy = "blbdId", fetch = FetchType.LAZY)
    private List<BoardOrg> boardOrgs;
}
