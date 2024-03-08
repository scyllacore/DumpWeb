package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step3ForDriveReportRegistrationMapper;
import org.springframework.http.ResponseEntity;
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
public class Step3ForDriveReportRegistrationService {

    private final CommonUtil commonUtil;
    private final Step3ForDriveReportRegistrationMapper step3Mapper;

    public int getUserIdFk() {
        return commonUtil.getLoginInfoBySession().getUserIdIdx();
    }

    public DriverDTO getDriverInfo() {
        return (DriverDTO) commonUtil.getInfoBySession("driverInfo");
    }


    @Transactional
    public ResponseEntity<String> saveDriveReport(DriveReportDTO driveReport) {
        driveReport.setWriterIdFk(getUserIdFk());
        driveReport.setDriverIdFk(getDriverInfo().getDriverId());

        if (driveReport.getDriveReportId() == 0) {
            step3Mapper.insertDriveReport(driveReport);
        } else if (driveReport.getUserType() == 1) {
            step3Mapper.updateSubmit(driveReport);
        } else {
            step3Mapper.updateDriveReport(driveReport);
        }

        return new ResponseEntity<String>(200, "저장 완료.");
    }

    public ResponseEntity<List<DriveReportDTO>> findDriveReportList(DriveReportDTO driveReport) {
        driveReport.setDriverIdFk(getDriverInfo().getDriverId());
        return new ResponseEntity<>(200, step3Mapper.selectDriveReportList(driveReport));
    }

    public ResponseEntity<DriveReportDTO> findDriveReport(DriveReportDTO driveReport) {
        driveReport.setDriverIdFk(getDriverInfo().getDriverId());
        return new ResponseEntity<>(200, step3Mapper.selectDriveReport(driveReport));
    }

    @Transactional
    public ResponseEntity<String> removeDriveReport(DriveReportDTO driveReport) {
        driveReport.setWriterIdFk(getUserIdFk());

        step3Mapper.deleteDriveReport(driveReport);

        return new ResponseEntity<String>(200, "삭제 완료.");
    }

    public ResponseEntity<List<SubmitterDTO>> findSubmitterList() {
        return new ResponseEntity<>(200, step3Mapper.selectSubmitterList());
    }
}
