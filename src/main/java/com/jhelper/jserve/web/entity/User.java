package com.jhelper.jserve.web.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TBSYS2002M")
public class User extends BaseEntity {

    @Id
    private String usrId;
    private String pw;
    private String useYn;
    private String usrRoleId;
    private String orgCd;
    private String lginYn;
    private Integer lginTryTcnt;
    private String pwUpdDtm;
    private String usrNm;
    private String mbphNo;
    private String emlAddr;
    private String gndrDvcd;

    @Lob
    private String poto;

    @MapsId("orgCd")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orgCd")
    private Org org;

}
