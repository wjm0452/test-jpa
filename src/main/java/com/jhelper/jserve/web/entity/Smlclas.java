package com.jhelper.jserve.web.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@IdClass(Smlclas.PK.class)
public class Smlclas extends BaseEntity {

    @Id
    private String lrgclasCd;

    @Id
    private String smlclasCd;

    private String smlclasCdNm;
    private Integer srtSeq;
    private String memo;
    private String useYn;

    @MapsId("lrgclasCd")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lrgclasCd")
    private Lrgclas lrgclas;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static public class PK implements Serializable {
        private String lrgclasCd;
        private String smlclasCd;
    }
}
