package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step3ForDriveReportRegistrationMapper {
    int insertDriveReport(DriveReportDTO.Request driveReport);

    int updateDriveReport(DriveReportDTO.Request driveReport);

    int updateSubmit(DriveReportDTO.Request driveReport);

    List<DriveReportDTO.Response> selectDriveReportList(DriveReportDTO.Request driveReport);

    DriveReportDTO.Response selectDriveReport(DriveReportDTO.Request driveReport);

    int deleteDriveReport(DriveReportDTO.Request driveReport);
    List<UserDetailDTO.Submitter> selectSubmitterList();

}
