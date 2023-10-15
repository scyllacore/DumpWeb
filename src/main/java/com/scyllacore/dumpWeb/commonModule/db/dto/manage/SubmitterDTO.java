package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class SubmitterDTO {
    private int submitterId;
    private String companyName;
    private String tel;
    private String managerName;
    private int userIdFk;
}
