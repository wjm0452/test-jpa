package com.jhelper.jserve.web.entity;

import java.io.Serializable;

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
@Table(name = "TBCOM2011M")
@IdClass(BoardOrg.PK.class)
public class BoardOrg extends BaseEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blbd_id", referencedColumnName = "blbdId")
    private Board blbdId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_cd", referencedColumnName = "orgCd")
    private Org orgCd;

    private String blthgWrtgPosbYn;
    private String orgPrscntAllIclsYn;
    private String useYn;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static public class PK implements Serializable {
        private String blbdId;
        private String orgCd;
    }
}