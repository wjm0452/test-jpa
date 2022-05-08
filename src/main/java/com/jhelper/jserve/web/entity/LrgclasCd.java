package com.jhelper.jserve.web.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

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
@Table(name = "TBSYSZ003C")
public class LrgclasCd extends BaseEntity {
    @Id
    private String lrgclasCd;
    private String lrgclasCdNm;
    private String cdDv;
    private Integer srtSeq;
    private String memo;
    private String useYn;

    @ToString.Exclude
    @OneToMany(mappedBy = "lrgclasCd", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SmlclasCd> smlclasCds;
}
