package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Step7ForSubmitterOrderRegistrationMapper {
    void insertDriveReport(DriveReportDTO driveReport);

    void insertDriveReportSub(DriveReportDTO driveReport);

}
