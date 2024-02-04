package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.PageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step6ForVehicleManageMileageViewerMapper {
    List<MileageDTO> selectMileageListByOption(PageDTO option);

    int countMileageListByOption(PageDTO option);

    void updateMileagePaymentChk(MileageSearchOptionDTO option);

}
