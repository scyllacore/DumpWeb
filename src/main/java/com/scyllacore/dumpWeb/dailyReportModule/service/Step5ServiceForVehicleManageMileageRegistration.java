package com.scyllacore.dumpWeb.dailyReportModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.TDrive;
import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
import com.scyllacore.dumpWeb.commonModule.db.mapper.dailyreport.Step5MapperForVehicleManageMileageRegistration;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
