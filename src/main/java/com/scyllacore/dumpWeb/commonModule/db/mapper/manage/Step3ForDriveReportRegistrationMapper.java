package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step3ForDriveReportRegistrationMapper {
    void insertDriveReport(DriveReportDTO driveReport);

    void updateDriveReport(DriveReportDTO driveReport);

    List<DriveReportDTO> selectDriveReportList(DriveReportDTO driveReportDTO);

    DriveReportDTO selectDriveReport(DriveReportDTO driveReportDTO);

    List<SubmitterDTO> selectSubmitterList();

    void deleteDriveReport(DriveReportDTO driveReport);

}
