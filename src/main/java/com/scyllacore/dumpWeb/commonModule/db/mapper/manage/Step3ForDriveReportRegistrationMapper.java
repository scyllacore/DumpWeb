package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;

import java.util.List;

public interface Step3ForDriveReportRegistrationMapper {
    long insertDriveReport(DriveReportDTO.Request driveReport);

    List<DriveReportDTO.Response> selectDriveReportList(DriveReportDTO.Request driveReport);

    DriveReportDTO.Response selectDriveReport(DriveReportDTO.Request driveReport);

    List<UserDetailDTO.Submitter> selectSubmitterList();

    long updateDriveReport(DriveReportDTO.Request driveReport);

    long updateSubmit(DriveReportDTO.Request driveReport);

    long deleteDriveReport(DriveReportDTO.Request driveReport);

}
