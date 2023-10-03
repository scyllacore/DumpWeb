package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.TDrive;
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


    public String saveMileage(TDrive tDrive) {

        tDrive.setCarNo(commonUtil.getLoginInfoBySession().getUserId());

        if (tDrive.getDriveID() == 0) {
            step5Mapper.insertTDrive(tDrive);
        } else {
            step5Mapper.updateTDrive(tDrive);
        }

        return "저장 완료.";
    }

    public List<TDrive> findMileageList(String date) {
        return step5Mapper.selectTDriveList(commonUtil.getLoginInfoBySession().getUserId(), date);
    }

    public void removeMileage(int driveID) {
        step5Mapper.deleteTDrive(commonUtil.getLoginInfoBySession().getUserId(), driveID);
    }

    public TDrive findMileage(int driveID) {
        return step5Mapper.selectTDriveDetails(commonUtil.getLoginInfoBySession().getUserId(), driveID);
    }
}
