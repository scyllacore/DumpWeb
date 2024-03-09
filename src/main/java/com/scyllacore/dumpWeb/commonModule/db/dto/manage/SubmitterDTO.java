package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class SubmitterDTO {
    private Long submitterId;
    private String company;
    private String tel;
    private String name;
    private Long userIdFk;
}
