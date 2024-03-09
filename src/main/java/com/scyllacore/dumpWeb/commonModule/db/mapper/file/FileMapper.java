package com.scyllacore.dumpWeb.commonModule.db.mapper.file;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.FileDTO;
public interface FileMapper {
    int insertFileInfoByGroupReportId(FileDTO fileDTO);

    FileDTO findFileInfoByFileId(Long fileId);

    void deleteFile(Long fileId);

}