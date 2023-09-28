package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.TDrive;
import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step5MapperForVehicleManageMileageRegistration;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class Step5ServiceForVehicleManageMileageRegistration {
    private final CommonUtil commonUtil;
    private final Step5MapperForVehicleManageMileageRegistration step5Mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Login getSessionLoginData() {
        return (Login) commonUtil.getSession().getAttribute("loginInfo");
    }


    public String saveTDrive(TDrive tDrive) {

        Map<String, Object> rtnMap = commonUtil.returnMap();

        try {
            tDrive.setCarNo(getSessionLoginData().getUserId());

            if (tDrive.getDriveID() == 0) {
                step5Mapper.insertTDrive(tDrive);
            } else {
                step5Mapper.updateTDrive(tDrive);
            }
            rtnMap.put("httpCode", 200);

        } catch (Exception e) {
            log.error("Exception[" + e.getMessage() + "]");
        }

        return commonUtil.jsonFormatTransfer(rtnMap);
    }

    public List<TDrive> findTDriveList(String date) {
        return step5Mapper.selectTDriveList(getSessionLoginData().getUserId(), date);
    }

    public void removeTDrive(int driveID) {
        step5Mapper.deleteTDrive(getSessionLoginData().getUserId(), driveID);
    }

    public TDrive findTDriveDetails(int driveID) {
        return step5Mapper.selectTDriveDetails(getSessionLoginData().getUserId(), driveID);
    }
}
