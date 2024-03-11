package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

public class UserDetailDTO {
    @Data
    static class DriverDTO {
        private Long driverId;
        private String carNo;
        private String name;
        private Long userIdFk;
    }

    @Data
    static class SubmitterDTO {
        private Long submitterId;
        private String company;
        private String tel;
        private String name;
        private Long userIdFk;
    }
}
