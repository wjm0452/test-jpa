package com.jhelper.jserve.web.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embedded;
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
@Table(name = "TBSYS2001M")
public class Org extends BaseEntity {
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

    @OneToMany(mappedBy = "org", fetch = FetchType.LAZY)
    private List<User> users;
}