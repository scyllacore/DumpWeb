package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step7ForDriveOrderRegistrationMapper {
    int insertDriveOrder(DriveReportDTO.Request driveReport);

    int updateDriveOrder(DriveReportDTO.Request driveReport);

    List<DriveReportDTO.Response> selectDriveOrderList(DriveReportDTO.Request driveReport);

    DriveReportDTO.Response selectDriveOrder(DriveReportDTO.Request driveReport);

    int deleteDriveOrder(DriveReportDTO.Request driveReport);

    List<UserDetailDTO.Driver> selectDriverList();

}
