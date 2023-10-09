package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step5ForVehicleManageMileageRegistrationMapper {
    void insertMileage(MileageDTO mileage);

    void updateMileage(MileageDTO mileage);

    List<MileageDTO> selectMileageList(String userID, String date);

    void deleteMileage(String userID, int driveID);

    MileageDTO selectMileage(int userId, int mileageId);
}
