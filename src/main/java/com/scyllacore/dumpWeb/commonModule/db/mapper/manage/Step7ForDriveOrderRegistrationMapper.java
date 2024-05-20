package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface Step7ForDriveOrderRegistrationMapper {
    long insertDriveOrder(DriveReportDTO.Request driveReport);

    long updateDriveOrder(DriveReportDTO.Request driveReport);

    List<DriveReportDTO.Response> selectDriveOrderList(DriveReportDTO.Request driveReport);

    DriveReportDTO.Response selectDriveOrder(DriveReportDTO.Request driveReport);

    long deleteDriveOrder(DriveReportDTO.Request driveReport);

    List<UserDetailDTO.Driver> selectDriverList();

}
