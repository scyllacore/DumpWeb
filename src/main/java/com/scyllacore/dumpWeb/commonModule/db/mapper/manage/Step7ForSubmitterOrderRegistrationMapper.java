package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step7ForSubmitterOrderRegistrationMapper {
    void insertDriveOrder(DriveReportDTO driveReport);

    void updateDriveOrder(DriveReportDTO driveReport);

    List<DriveReportDTO> selectDriveOrderList(DriveReportDTO driveReport);

    DriveReportDTO selectDriveOrder(DriveReportDTO driveReport);

    void deleteDriveOrder(DriveReportDTO driveReport);

    List<DriverDTO> selectDriverList();

}
