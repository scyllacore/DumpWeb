package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

public class UserDetailDTO {
    @Data
    public class Driver {
        private Long driverId;
        private String carNo;
        private String name;
        private Long userIdFk;
    }

    @Data
    public class Submitter {
        private Long submitterId;
        private String company;
        private String tel;
        private String name;
        private Long userIdFk;
    }
}
