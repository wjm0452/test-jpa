package com.jhelper.jserve.web.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TBSYS2001H")
@IdClass(OrgHistory.PK.class)
public class OrgHistory {

    @Id
    private String orgCd;
    @Id
    private String orgHistRegId;
    @Id
    private String orgHistRegDtm;

    private String orgUpdTypCd;
    private String orgNm;
    private String orgPathNm;
    private String orgLvl;
    private String prtsOrgCd;
    private String bizDvcd;
    private String orgKindDvcd;
    private String srtSeq;
    private String useYn;

    @MapsId("orgCd")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orgCd")
    private Org org;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static public class PK implements Serializable {
        private String orgCd;
        private String orgHistRegId;
        private String orgHistRegDtm;
    }
}
