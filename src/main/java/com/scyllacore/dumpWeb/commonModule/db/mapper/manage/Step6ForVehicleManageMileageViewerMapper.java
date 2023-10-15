package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step6ForVehicleManageMileageViewerMapper {
    List<MileageDTO> selectMileageListByOption(MileageSearchOptionDTO option);

    void updateMileagePaymentChk(MileageSearchOptionDTO option);

}
