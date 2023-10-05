package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step5MapperForVehicleManageMileageRegistration;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Step5ServiceForVehicleManageMileageRegistration {
    private final CommonUtil commonUtil;
    private final Step5MapperForVehicleManageMileageRegistration step5Mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public String saveMileage(MileageDTO mileage) {

        mileage.setUserId_IDX_FK(Integer.parseInt(commonUtil.getLoginInfoBySession().getUserIdIDX()));

        if (mileage.getDriveId() == 0) {
            step5Mapper.insertMileage(mileage);
        } else {
            step5Mapper.updateMileage(mileage);
        }

        return "저장 완료.";
    }

    public List<MileageDTO> findMileageList(String date) {
        return step5Mapper.selectMileageList(commonUtil.getLoginInfoBySession().getUserId(), date);
    }

    public void removeMileage(int driveId) {
        step5Mapper.deleteMileage(commonUtil.getLoginInfoBySession().getUserId(), driveId);
    }

    public MileageDTO findMileage(int driveId) {
        return step5Mapper.selectMileage(commonUtil.getLoginInfoBySession().getUserId(), driveId);
    }
}
