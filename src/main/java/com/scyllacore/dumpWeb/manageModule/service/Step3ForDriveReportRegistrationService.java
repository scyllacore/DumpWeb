package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step3ForDriveReportRegistrationMapper;
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
public class Step3ForDriveReportRegistrationService {

    private final CommonUtil commonUtil;
    private final Step3ForDriveReportRegistrationMapper step3Mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public int getUserIdFK() {
        return Integer.parseInt(commonUtil.getLoginInfoBySession().getUserIdIdx());
    }

    public ResponseDTO<String> saveDriveReport(DriveReportDTO driveReport){
        driveReport.setWriterIdIdxFk(getUserIdFK());

        if(driveReport.getReportIdx() == 0){
            step3Mapper.insertDriveReport(driveReport);
        }else{
            step3Mapper.updateDriveReport(driveReport);
        }

        return new ResponseDTO<String>(200,"저장 완료.");
    }

    public ResponseDTO<List<DriveReportDTO>> findDriveReportList(DriveReportDTO driveReport){
        driveReport.setWriterIdIdxFk(getUserIdFK());

        return new ResponseDTO<>(200,step3Mapper.selectDriveReportList(driveReport));
    }

    public ResponseDTO<DriveReportDTO> findDriveReport(DriveReportDTO driveReport){
        driveReport.setWriterIdIdxFk(getUserIdFK());

        return new ResponseDTO<>(200,step3Mapper.selectDriveReport(driveReport));
    }


    public ResponseDTO<String> removeDriveReport(DriveReportDTO driveReport){
        driveReport.setWriterIdIdxFk(getUserIdFK());

        step3Mapper.deleteDriveReport(driveReport);

        return new ResponseDTO<String>(200,"삭제 완료.");
    }

}
