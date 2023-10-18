package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step7ForSubmitterOrderRegistrationMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Step7ForSubmitterOrderRegistrationService {

    private final CommonUtil commonUtil;
    private final Step7ForSubmitterOrderRegistrationMapper step7Mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public int getUserIdFK() {
        return commonUtil.getLoginInfoBySession().getUserIdIdx();
    }

    public ResponseDTO<String> saveDriveOrder(DriveReportDTO driveReport) {
        driveReport.setWriterIdFk(getUserIdFK());

        if (driveReport.getDriveReportId() == 0) {
            step7Mapper.insertDriveOrder(driveReport);
        } else {
            step7Mapper.updateDriveOrder(driveReport);
        }

        return new ResponseDTO<String>(200, "저장 완료.");
    }

    public ResponseDTO<List<DriveReportDTO>> findDriveOrderList(DriveReportDTO driveReport) {
        driveReport.setWriterIdFk(getUserIdFK());

        return new ResponseDTO<>(200, step7Mapper.selectDriveOrderList(driveReport));
    }

    public ResponseDTO<DriveReportDTO> findDriveOrder(DriveReportDTO driveReport) {
        driveReport.setWriterIdFk(getUserIdFK());

        System.out.println(step7Mapper.selectDriveOrder(driveReport));

        return new ResponseDTO<>(200, step7Mapper.selectDriveOrder(driveReport));
    }


    public ResponseDTO<String> removeDriveOrder(DriveReportDTO driveReport) {
        driveReport.setWriterIdFk(getUserIdFK());

        step7Mapper.deleteDriveOrder(driveReport);

        return new ResponseDTO<String>(200, "삭제 완료.");
    }

    public ResponseDTO<List<DriverDTO>> findDriverList() {
        return new ResponseDTO<>(200, step7Mapper.selectDriverList());
    }
}
