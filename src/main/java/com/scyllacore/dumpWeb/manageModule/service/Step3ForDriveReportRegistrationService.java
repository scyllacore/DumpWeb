package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.constant.OperationStatus;
import com.scyllacore.dumpWeb.commonModule.constant.ResponseType;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step3ForDriveReportRegistrationMapper;
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
public class Step3ForDriveReportRegistrationService {

    private final SessionUtil sessionUtil;
    private final Step3ForDriveReportRegistrationMapper step3Mapper;

    @Getter
    public enum Step3Flag {
        NEW_DRIVE_REPORT(0),
        ONLY_CHANGE_BY_DRIVER(0),
        ;

        int value;

        Step3Flag(int value) {
            this.value = value;
        }
    }

    @Transactional
    public ResponseEntity<ResponseDTO<String>> saveDriveReport(DriveReportDTO.Request driveReport) {
        driveReport.setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());
        driveReport.setDriverIdFk(sessionUtil.getDriverInfo().getDriverId());

        if (driveReport.getDriveReportId() == Step3Flag.NEW_DRIVE_REPORT.getValue()) {
            insertDriveReport(driveReport);
        } else if (driveReport.getUserType() == Step3Flag.ONLY_CHANGE_BY_DRIVER.getValue()) {
            updateDriveReport(driveReport);
        } else {
            updateSubmit(driveReport);
        }

        return ResponseEntity.ok(new ResponseDTO<>("저장 완료."));
    }

    private void insertDriveReport(DriveReportDTO.Request driveReport) {
        if (step3Mapper.insertDriveReport(driveReport) <= OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
    }

    private void updateSubmit(DriveReportDTO.Request driveReport) {
        if (step3Mapper.updateSubmit(driveReport) <= OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
    }

    private void updateDriveReport(DriveReportDTO.Request driveReport) {
        if (step3Mapper.updateDriveReport(driveReport) <= OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
    }

    public ResponseEntity<List<DriveReportDTO.Response>> findDriveReportList(DriveReportDTO.Request driveReport) {
        driveReport.setDriverIdFk(sessionUtil.getDriverInfo().getDriverId());
        return ResponseEntity.ok(step3Mapper.selectDriveReportList(driveReport));
    }

    public ResponseEntity<DriveReportDTO.Response> findDriveReport(DriveReportDTO.Request driveReport) {
        driveReport.setDriverIdFk(sessionUtil.getDriverInfo().getDriverId());

        DriveReportDTO.Response response = step3Mapper.selectDriveReport(driveReport);

        if (response.getDriveReportId() == null) {
            throw new RestApiException(ResponseType.NOT_FOUND);
        }

        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<ResponseDTO<String>> removeDriveReport(DriveReportDTO.Request driveReport) {
        driveReport.setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());

        if (step3Mapper.deleteDriveReport(driveReport) <= OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }

        return ResponseEntity.ok(new ResponseDTO<>("삭제 완료."));
    }

    public ResponseEntity<List<UserDetailDTO.Submitter>> findSubmitterList() {
        return ResponseEntity.ok(step3Mapper.selectSubmitterList());
    }
}
