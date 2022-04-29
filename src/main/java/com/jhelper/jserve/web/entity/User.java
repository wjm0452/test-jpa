package com.jhelper.jserve.web.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "TBSYS2002M")
public class User implements Serializable {

    @Id
    private String usrId;
    private String pw;
    private String useYn;
    private String usrRoleId;

    private String lginYn;
    private int lginTryTcnt;
    private String pwUpdDtm;
    private String usrNm;
    private String mbphNo;
    private String emlAddr;
    private String gndrDvcd;

    @Lob
    private String poto;
    private String regId;
    private String regDtm;
    private String regPgmId;
    private String lstUpdId;
    private String lstUpdDtm;
    private String lstUpdPgmId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "org_cd", referencedColumnName = "orgCd")
    private Org org;

}
