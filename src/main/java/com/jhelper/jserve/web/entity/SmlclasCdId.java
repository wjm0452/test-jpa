package com.jhelper.jserve.web.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmlclasCdId implements Serializable {
    private String lrgclasCd;
    private String smlclasCd;
}
