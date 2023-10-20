package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
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

    public int getUserIdFk() {
        return commonUtil.getLoginInfoBySession().getUserIdIdx();
    }

    public SubmitterDTO getSubmitterInfo() {
        return (SubmitterDTO) commonUtil.getInfoBySession("submitterInfo");
    }

    public ResponseDTO<String> saveDriveOrder(DriveReportDTO driveReport) {
        driveReport.setWriterIdFk(getUserIdFk());

        System.out.println(driveReport);

        if (driveReport.getDriveReportId() == 0) {
            step7Mapper.insertDriveOrder(driveReport);

        } else if (driveReport.getUserType() == 1) {
            step7Mapper.updateDriveOrder(driveReport);
        }

        return new ResponseDTO<String>(200, "저장 완료.");
    }

    public ResponseDTO<List<DriveReportDTO>> findDriveOrderList(DriveReportDTO driveReport) {
        driveReport.setSubmitterIdFk(getSubmitterInfo().getSubmitterId());

        return new ResponseDTO<>(200, step7Mapper.selectDriveOrderList(driveReport));
    }

    public ResponseDTO<DriveReportDTO> findDriveOrder(DriveReportDTO driveReport) {
        driveReport.setSubmitterIdFk(getSubmitterInfo().getSubmitterId());

        return new ResponseDTO<>(200, step7Mapper.selectDriveOrder(driveReport));
    }


    public ResponseDTO<String> removeDriveOrder(DriveReportDTO driveReport) {
        driveReport.setWriterIdFk(getUserIdFk());

        step7Mapper.deleteDriveOrder(driveReport);

        return new ResponseDTO<String>(200, "삭제 완료.");
    }

    public ResponseDTO<List<DriverDTO>> findDriverList() {
        return new ResponseDTO<>(200, step7Mapper.selectDriverList());
    }
}
