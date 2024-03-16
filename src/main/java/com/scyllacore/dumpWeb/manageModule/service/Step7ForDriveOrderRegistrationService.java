package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.constant.OperationStatus;
import com.scyllacore.dumpWeb.commonModule.constant.ResponseType;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step7ForDriveOrderRegistrationMapper;
import com.scyllacore.dumpWeb.commonModule.exception.RestApiException;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.SessionUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Step7ForDriveOrderRegistrationService {

    private final SessionUtil sessionUtil;
    private final Step7ForDriveOrderRegistrationMapper step7Mapper;

    @Getter
    public enum Step7Flag {
        NEW_ORDER_REPORT(0),
        ONLY_CHANGE_BY_SUBMITTER(1),
        ;

        int value;

        Step7Flag(int value) {
            this.value = value;
        }
    }

    @Transactional
    public ResponseEntity<ResponseDTO<String>> saveDriveOrder(DriveReportDTO.Request driveReport) {
        driveReport.setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());
        driveReport.setProgress("배차");

        if (driveReport.getDriveReportId() == Step7Flag.NEW_ORDER_REPORT.getValue()) {
            insertDriveOrder(driveReport);
        } else if (driveReport.getUserType() == Step7Flag.ONLY_CHANGE_BY_SUBMITTER.getValue()) {
            updateDriveOrder(driveReport);
        }

        return ResponseEntity.ok(new ResponseDTO<>("저장 완료."));
    }

    private void insertDriveOrder(DriveReportDTO.Request driveReport) {
        if (step7Mapper.insertDriveOrder(driveReport) == OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
    }

    private void updateDriveOrder(DriveReportDTO.Request driveReport) {
        if (step7Mapper.updateDriveOrder(driveReport) <= OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
    }

    public ResponseEntity<List<DriveReportDTO.Response>> findDriveOrderList(DriveReportDTO.Request driveReport) {
        driveReport.setSubmitterIdFk(sessionUtil.getSubmitterInfo().getSubmitterId());
        return ResponseEntity.ok(step7Mapper.selectDriveOrderList(driveReport));

    }

    public ResponseEntity<DriveReportDTO.Response> findDriveOrder(DriveReportDTO.Request driveReport) {
        driveReport.setSubmitterIdFk(sessionUtil.getSubmitterInfo().getSubmitterId());

        DriveReportDTO.Response response = step7Mapper.selectDriveOrder(driveReport);

        if (response.getDriveReportId() == null) {
            throw new RestApiException(ResponseType.NOT_FOUND);
        }

        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<ResponseDTO<String>> removeDriveOrder(DriveReportDTO.Request driveReport) {
        driveReport.setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());

        if (step7Mapper.deleteDriveOrder(driveReport) <= OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }

        return ResponseEntity.ok(new ResponseDTO<>("삭제 완료."));

    }

    public ResponseEntity<List<UserDetailDTO.Driver>> findDriverList() {
        return ResponseEntity.ok(step7Mapper.selectDriverList());
    }
}
