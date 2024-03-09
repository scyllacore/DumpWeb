package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class DriverDTO {
    private Long driverId;
    private String carNo;
    private String name;
    private Long userIdFk;
}
