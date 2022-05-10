package com.jhelper.jserve.web.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @CreatedBy
    @Column(updatable = false)
    String regId;

    @CreatedDate
    @Column(updatable = false)
    String regDtm;

    @Column(updatable = false)
    String regPgmId;

    @LastModifiedBy
    String lstUpdId;

    @LastModifiedDate
    String lstUpdDtm;

    String lstUpdPgmId;

    @PrePersist
    public void prePersist() {
        lstUpdId = regId = "system";
        lstUpdDtm = regDtm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        lstUpdPgmId = regPgmId = "system";
    }

    @PreUpdate
    public void preUpdate() {
        lstUpdId = "update";
        lstUpdDtm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        lstUpdPgmId = "update";
    }
}
