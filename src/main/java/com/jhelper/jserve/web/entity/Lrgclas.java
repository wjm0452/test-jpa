package com.jhelper.jserve.web.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TBSYSZ003C")
public class Lrgclas extends BaseEntity {
    @Id
    private String lrgclasCd;
    private String lrgclasCdNm;

    private String cdDv;
    private Integer srtSeq;
    private String memo;
    private String useYn;

    @ToString.Exclude
    @OneToMany(mappedBy = "lrgclas", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Smlclas> smlclass;
}
