package com.jhelper.jserve.web.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "TBSYSZ004C")
@IdClass(SmlclasCd.PK.class)
public class SmlclasCd extends BaseEntity {

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "lrgclas_cd", referencedColumnName = "lrgclasCd")
    private LrgclasCd lrgclasCd;

    @Id
    private String smlclasCd;

    private String smlclasCdNm;
    private Integer srtSeq;
    private String memo;
    private String useYn;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static public class PK implements Serializable {
        private String lrgclasCd;
        private String smlclasCd;
    }
}
