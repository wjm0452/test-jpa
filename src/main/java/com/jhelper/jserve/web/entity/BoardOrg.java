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
@Table(name = "TBCOM2011M")
@IdClass(BoardOrgId.class)
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
}