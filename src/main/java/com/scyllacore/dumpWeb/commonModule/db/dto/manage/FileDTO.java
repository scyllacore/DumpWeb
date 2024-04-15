package com.scyllacore.dumpWeb.commonModule.db.dto.manage;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileDTO {

    private Long fileId;
    private String fileName;
    private String fileExt;
    private String uuid;
    private Long groupReportIdFk;

    @Builder
    public FileDTO(Long fileId, String fileName, String fileExt, String uuid, Long groupReportIdFk) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileExt = fileExt;
        this.uuid = uuid;
        this.groupReportIdFk = groupReportIdFk;
    }
}
