package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface Step3ForDriveReportRegistrationMapper {
    long insertDriveReport(DriveReportDTO.Request driveReport);

    long updateDriveReport(DriveReportDTO.Request driveReport);

    long updateSubmit(DriveReportDTO.Request driveReport);

    List<DriveReportDTO.Response> selectDriveReportList(DriveReportDTO.Request driveReport);

    DriveReportDTO.Response selectDriveReport(DriveReportDTO.Request driveReport);

    long deleteDriveReport(DriveReportDTO.Request driveReport);
    List<UserDetailDTO.Submitter> selectSubmitterList();

}
