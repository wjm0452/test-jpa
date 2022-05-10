package com.jhelper.jserve.web.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TBCOM2014M")
@IdClass(BoardHeader.PK.class)
public class BoardHeader {

    @Id
    private String blbdId;

    @Id
    private Integer pfcSeq;

    private String pfc;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static public class PK implements Serializable {
        private String blbdId;
        private Integer pfcSeq;
    }
}
