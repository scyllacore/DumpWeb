package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step5ForVehicleManageMileageRegistrationMapper {
    int insertMileage(MileageDTO.Request mileage);

    int updateMileage(MileageDTO.Request mileage);

    int deleteMileage(MileageDTO.Request mileage);

    List<MileageDTO.Response> selectMileageList(MileageDTO.Request mileage);

    MileageDTO.Response selectMileage(MileageDTO.Request mileage);
}
