package com.jhelper.jserve.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardAdminDto {
    private String blbdId;
    private String blbdNm;
    private String usrId;
    private String useYn;
}
