package com.jhelper.jserve.web.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "TBSYS2001M")
public class Org extends BaseEntity {
    @Id
    private String orgCd;
    private String orgNm;
    private Integer orgLvl;
    private String prtsOrgCd;
    private String bizDvcd;
    private String orgKindDvcd;
    private String orgPathNm;
    private Integer srtSeq;
    private String useYn;
}