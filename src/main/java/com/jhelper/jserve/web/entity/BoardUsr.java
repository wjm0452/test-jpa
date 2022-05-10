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
@Table(name = "TBCOM2012M")
@IdClass(BoardUsr.PK.class)
public class BoardUsr {

    @Id
    private String blbdId;

    @Id
    private String orgCd;

    @Id
    private String usrId;

    private String useYn;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static public class PK implements Serializable {
        private String blbdId;
        private String orgCd;
        private String usrId;
    }
}
