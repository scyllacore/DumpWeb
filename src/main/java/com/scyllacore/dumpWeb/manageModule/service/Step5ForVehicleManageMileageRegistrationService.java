package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.constant.OperationStatus;
import com.scyllacore.dumpWeb.commonModule.constant.ResponseType;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step5ForVehicleManageMileageRegistrationMapper;
import com.scyllacore.dumpWeb.commonModule.exception.RestApiException;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.SessionUtil;
import com.scyllacore.dumpWeb.manageModule.constants.Step5Flags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public ResponseEntity<ResponseDTO<String>> saveMileage(MileageDTO.Request mileage) {

        mileage.setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());

        if (mileage.getMileageId() == Step5Flags.NEW_MILEAGE.getValue()) {
            insertMileage(mileage);
        } else {
            updateMileage(mileage);
        }

        return ResponseEntity.ok(new ResponseDTO<>("저장 완료."));
    }

    private void insertMileage(MileageDTO.Request mileage) {
        if (step5Mapper.insertMileage(mileage) <= OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
    }

    private void updateMileage(MileageDTO.Request mileage) {
        if (step5Mapper.updateMileage(mileage) <= OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
    }

    public ResponseEntity<List<MileageDTO.Response>> findMileageList(MileageDTO.Request mileage) {
        mileage.setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());
        return ResponseEntity.ok(step5Mapper.selectMileageList(mileage));
    }

    @Transactional
    public ResponseEntity<ResponseDTO<String>> removeMileage(MileageDTO.Request mileage) {
        mileage.setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());

        if (step5Mapper.deleteMileage(mileage) <= OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }

        return ResponseEntity.ok(new ResponseDTO<>("정상적으로 삭제되었습니다."));
    }

    public ResponseEntity<MileageDTO.Response> findMileage(MileageDTO.Request mileage) {
        mileage.setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());
        MileageDTO.Response response = step5Mapper.selectMileage(mileage);

        if (response.getMileageId() == null) {
            throw new RestApiException(ResponseType.NOT_FOUND);
        }

        return ResponseEntity.ok(response);
    }
}
