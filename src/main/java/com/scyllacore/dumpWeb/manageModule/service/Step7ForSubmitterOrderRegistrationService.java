package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step7ForSubmitterOrderRegistrationMapper;
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
public class Step7ForSubmitterOrderRegistrationService {

    private final CommonUtil commonUtil;
    private final Step7ForSubmitterOrderRegistrationMapper step7Mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public int getUserIdFk() {
        return commonUtil.getLoginInfoBySession().getUserIdIdx();
    }

    public SubmitterDTO getSubmitterInfo() {
        return (SubmitterDTO) commonUtil.getInfoBySession("submitterInfo");
    }

    @Transactional
    public ResponseEntity<String> saveDriveOrder(DriveReportDTO driveReport) {
        driveReport.setWriterIdFk(getUserIdFk());
        driveReport.setProgress("배차");

        if (driveReport.getDriveReportId() == 0) {
            step7Mapper.insertDriveOrder(driveReport);

        } else if (driveReport.getUserType() == 1) {
            step7Mapper.updateDriveOrder(driveReport);
        }

        return new ResponseEntity<String>(200, "저장 완료.");
    }

    public ResponseEntity<List<DriveReportDTO>> findDriveOrderList(DriveReportDTO driveReport) {
        driveReport.setSubmitterIdFk(getSubmitterInfo().getSubmitterId());

        return new ResponseEntity<>(200, step7Mapper.selectDriveOrderList(driveReport));
    }

    public ResponseEntity<DriveReportDTO> findDriveOrder(DriveReportDTO driveReport) {
        driveReport.setSubmitterIdFk(getSubmitterInfo().getSubmitterId());

        return new ResponseEntity<>(200, step7Mapper.selectDriveOrder(driveReport));
    }

    @Transactional
    public ResponseEntity<String> removeDriveOrder(DriveReportDTO driveReport) {
        driveReport.setWriterIdFk(getUserIdFk());

        step7Mapper.deleteDriveOrder(driveReport);

        return new ResponseEntity<String>(200, "삭제 완료.");
    }

    public ResponseEntity<List<DriverDTO>> findDriverList() {
        return new ResponseEntity<>(200, step7Mapper.selectDriverList());
    }
}
