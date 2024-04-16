package com.scyllacore.dumpWeb.commonModule.db.mapper.file;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.FileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    int insertFileInfoByGroupReportId(FileDTO fileDTO);

    FileDTO findFileInfoByFileId(Long fileId);

    int deleteFile(Long fileId);

}