package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileDTO {
    private Long fileId;
    private String fileName;
    private String fileExt;
    private String uuid;

    private Long groupReportIdFk;
}
