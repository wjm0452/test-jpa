package com.jhelper.jserve.web.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
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
    private Integer srtSeq;
    private String blbdDesc;
    private String blbdUseYn;
    private String allOrgRlseYn;
    private String blbdRlseYn;
    private String blthgAlrmUseYn;;
    private String pfcUseYn;
    private String clsdDispYn;
    private String replyPmssYn;

    @ToString.Exclude
    @MapsId("blbdId")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "blbdId")
    private List<BoardOrg> boardOrgs;

    @ToString.Exclude
    @MapsId("blbdId")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "blbdId")
    private List<BoardHeader> boaredHeaders;

    @ToString.Exclude
    @MapsId("blbdId")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "blbdId")
    private List<BoardAdmin> boardAdmins;
}
