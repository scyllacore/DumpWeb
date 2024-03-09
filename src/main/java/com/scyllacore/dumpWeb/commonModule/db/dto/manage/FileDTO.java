package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Data;

@Data
public class FileDTO {

    private Long fileId;
    private String fileName;
    private String fileExt;
    private String uuid;

    private Long groupReportIdFk;
}
