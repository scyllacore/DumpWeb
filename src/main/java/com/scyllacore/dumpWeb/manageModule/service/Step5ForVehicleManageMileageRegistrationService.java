package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.constant.Flag;
import com.scyllacore.dumpWeb.commonModule.constant.ResponseType;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step5ForVehicleManageMileageRegistrationMapper;
import com.scyllacore.dumpWeb.commonModule.exception.RestApiException;
import com.scyllacore.dumpWeb.commonModule.util.SessionUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Step5ForVehicleManageMileageRegistrationService {
    private final SessionUtil sessionUtil;
    private final Step5ForVehicleManageMileageRegistrationMapper step5Mapper;

    @Getter
    public enum Step5Flag {
        NEW_MILEAGE(0);

        int value;

        Step5Flag(int value) {
            this.value = value;
        }
    }

    @Transactional
    public ResponseEntity<String> saveMileage(MileageDTO.Request mileage) {

        mileage.setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());

        if (mileage.getMileageId() == Step5Flag.NEW_MILEAGE.getValue()) {
            insertMileage(mileage);
        } else {
            updateMileage(mileage);
        }

        return ResponseEntity.ok("저장 완료.");
    }

    private void insertMileage(MileageDTO.Request mileage) {
        if (step5Mapper.insertMileage(mileage) <= Flag.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
    }

    private void updateMileage(MileageDTO.Request mileage) {
        if (step5Mapper.updateMileage(mileage) <= Flag.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
    }

    public ResponseEntity<List<MileageDTO.Response>> findMileageList(MileageDTO.Request mileage) {
        mileage.setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());
        return ResponseEntity.ok(step5Mapper.selectMileageList(mileage));

    }

    @Transactional
    public ResponseEntity<String> removeMileage(MileageDTO.Request mileage) {
        mileage.setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());


        if (step5Mapper.deleteMileage(mileage) <= Flag.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
        return ResponseEntity.ok("정상적으로 삭제되었습니다.");
    }

    public ResponseEntity<MileageDTO.Response> findMileage(MileageDTO.Request mileage) {
        mileage.setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());
        MileageDTO.Response response = step5Mapper.selectMileage(mileage);

        if(response.getMileageId() == null){
            throw new RestApiException(ResponseType.NOT_FOUND);
        }

        return ResponseEntity.ok(response);
    }
}
