package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step5ForVehicleManageMileageRegistrationMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Step5ForVehicleManageMileageRegistrationService {
    private final CommonUtil commonUtil;
    private final Step5ForVehicleManageMileageRegistrationMapper step5Mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public ResponseDTO<String> saveMileage(MileageDTO mileage) {

        mileage.setUserIdIdxFk(Integer.parseInt(commonUtil.getLoginInfoBySession().getUserIdIdx()));
        mileage.setCarNo(commonUtil.getLoginInfoBySession().getUserId());

        if (mileage.getDriveId() == 0) {
            step5Mapper.insertMileage(mileage);
        } else {
            step5Mapper.updateMileage(mileage);
        }

        return new ResponseDTO<>(200,"저장 완료.");
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
