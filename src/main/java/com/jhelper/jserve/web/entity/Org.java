package com.jhelper.jserve.web.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TBSYS2001M")
public class Org implements Serializable {
    @Id
    private String orgCd;
    private String orgNm;
    private long orgLvl;
    private String prtsOrgCd;
    private String bizDvcd;
    private String orgKindDvcd;
    private String orgPathNm;
    private long srtSeq;
    private String useYn;
    private String regId;
    private String regDtm;
    private String regPgmId;
    private String lstUpdId;
    private String lstUpdDtm;
    private String lstUpdPgmId;

    @OneToMany(mappedBy = "org", fetch = FetchType.LAZY)
    private Set<User> users;
}