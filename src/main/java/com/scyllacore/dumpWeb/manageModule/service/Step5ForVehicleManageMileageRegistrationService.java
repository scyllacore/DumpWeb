package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step5ForVehicleManageMileageRegistrationMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Step5ForVehicleManageMileageRegistrationService {
    private final CommonUtil commonUtil;
    private final Step5ForVehicleManageMileageRegistrationMapper step5Mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public int getUserIdFk() {
        return commonUtil.getLoginInfoBySession().getUserIdIdx();
    }

    public String getUserId(){
        return commonUtil.getLoginInfoBySession().getUserId();
    }

    @Transactional
    public ResponseDTO<String> saveMileage(MileageDTO mileage) {

        mileage.setWriterIdFk(getUserIdFk());

        if (mileage.getMileageId() == 0) {
            step5Mapper.insertMileage(mileage);
        } else {
            step5Mapper.updateMileage(mileage);
        }

        return new ResponseDTO<>(200, "저장 완료.");
    }

    public ResponseDTO<List<MileageDTO>> findMileageList(MileageDTO mileage) {
        mileage.setWriterIdFk(getUserIdFk());

        return new ResponseDTO<>(200,
                step5Mapper.selectMileageList(mileage));
    }

    @Transactional
    public ResponseDTO<String> removeMileage(MileageDTO mileage) {
        mileage.setWriterIdFk(getUserIdFk());

        step5Mapper.deleteMileage(mileage);

        return new ResponseDTO<>(200, "정상적으로 삭제되었습니다.");
    }

    public ResponseDTO<MileageDTO> findMileage(MileageDTO mileage) {
        mileage.setWriterIdFk(getUserIdFk());

        return new ResponseDTO<>(200, step5Mapper.selectMileage(mileage));
    }
}
