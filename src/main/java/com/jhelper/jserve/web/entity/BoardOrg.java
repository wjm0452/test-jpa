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
@Table(name = "TBCOM2011M")
@IdClass(BoardOrg.PK.class)
public class BoardOrg extends BaseEntity {

    @Id
    private String blbdId;

    @Id
    private String orgCd;

    private String blthgWrtgPosbYn;
    private String orgPrscntAllIclsYn;
    private String useYn;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static public class PK implements Serializable {
        private String blbdId;
        private String orgCd;
    }
}