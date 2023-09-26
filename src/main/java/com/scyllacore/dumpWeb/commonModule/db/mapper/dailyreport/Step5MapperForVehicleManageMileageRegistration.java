package com.scyllacore.dumpWeb.commonModule.db.mapper.dailyreport;

import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.TDrive;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step5MapperForVehicleManageMileageRegistration {
    void insertTDrive(TDrive tDrive);

    void updateTDrive(TDrive tDrive);

    List<TDrive> selectTDriveList(String userID, String date);

    void deleteTDrive(String userID, int driveID);

    TDrive selectTDriveDetails(String userID, int driveID);
}
