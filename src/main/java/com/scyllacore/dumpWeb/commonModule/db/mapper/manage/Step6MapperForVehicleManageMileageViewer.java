package com.scyllacore.dumpWeb.commonModule.db.mapper.manage;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SearchOption;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.TDrive;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Step6MapperForVehicleManageMileageViewer {
    List<TDrive> selectMileageListByOption(SearchOption option);

    void updateMileageChk2ForApprove(SearchOption option);


    void updateMileageChk2ForCancel(SearchOption option);
}
