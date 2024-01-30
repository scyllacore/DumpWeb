package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step9ForGroupDriveReportRegistrationMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import com.scyllacore.dumpWeb.commonModule.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RequiredArgsConstructor
@Service
public class Step9ForGroupDriveReportRegistrationService {
    private final CommonUtil commonUtil;
    private final Step9ForGroupDriveReportRegistrationMapper step9Mapper;
    private final FileUtil fileUtil;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public int getUserIdFk() {
        return commonUtil.getLoginInfoBySession().getUserIdIdx();
    }

    public DriverDTO getDriverInfo() {
        return (DriverDTO) commonUtil.getInfoBySession("driverInfo");
    }

    public ResponseDTO<String> saveGroupDriveReport(GroupDriveReportDTO groupReport, MultipartFile imageFile) {
        groupReport.setGroupWriterIdFk(getUserIdFk());
        groupReport.setGroupDriverIdFk(getDriverInfo().getDriverId());

        if (groupReport.getGroupReportId() == 0) {
            this.insertGroupDriveReport(groupReport, imageFile);
        } else if (groupReport.getGroupUserType() == 1) {
            this.updateGroupSubmit(groupReport);
        } else {
            this.updateDriveReports(groupReport);
        }

        return new ResponseDTO<String>(200, "저장 완료.");
    }

    public void insertGroupDriveReport(GroupDriveReportDTO newGroupReport, MultipartFile imageFile) {
        step9Mapper.insertGroupDriveReport(newGroupReport);

        if (imageFile != null) {
            uploadFileByGroupReportId(imageFile, newGroupReport.getGroupReportId());
        }

        List<DriveReportDTO> prvDriveReport = new ArrayList<>();

        for (int i = 0; i < newGroupReport.getDriveReports().size(); i++) {
            DriveReportDTO driveReport = newGroupReport.getDriveReports().get(i);
            driveReport.setWriterIdFk(getUserIdFk());
            driveReport.setGroupReportIdFk(newGroupReport.getGroupReportId());

            if (driveReport.getDriveReportId() != 0) {
                prvDriveReport.add(driveReport);
                newGroupReport.getDriveReports().remove(i);
            }
        }

        if (!prvDriveReport.isEmpty()) {
            step9Mapper.updateDriveReports(prvDriveReport);
        }

        if (!newGroupReport.getDriveReports().isEmpty()) {
            step9Mapper.insertDriveReports(newGroupReport.getDriveReports());
        }
    }

    public void updateGroupSubmit(GroupDriveReportDTO groupReport) {
        step9Mapper.updateGroupSubmit(groupReport);
        step9Mapper.updateReportsSubmit(groupReport.getDriveReports());
    }

    public void updateDriveReports(GroupDriveReportDTO newGroupReport) {
        step9Mapper.updateGroupDriveReport(newGroupReport);

        Set<Integer> driveIds = new HashSet<>();

        List<Integer> prvDriveReportIds = step9Mapper
                .selectDriveReportIdsByGroupReportId(newGroupReport.getGroupReportId());


        for (Integer id : prvDriveReportIds) {
            driveIds.add(id);
        }

        List<DriveReportDTO> newDriveReports = newGroupReport.getDriveReports();

        for (DriveReportDTO driveReport : newDriveReports) {
            driveIds.remove(driveReport.getDriveReportId());
        }

        List<DriveReportDTO> driveReportsForInsert = new ArrayList<>();
        List<DriveReportDTO> driveReportsForUpdate = new ArrayList<>();

        for (DriveReportDTO driveReport : newDriveReports) {
            driveReport.setGroupReportIdFk(newGroupReport.getGroupReportId());

            if (driveReport.getDriveReportId() == 0) {
                driveReportsForInsert.add(driveReport);
            } else {
                driveReportsForUpdate.add(driveReport);
            }
        }

        if (!driveReportsForInsert.isEmpty()) {
            step9Mapper.insertDriveReports(driveReportsForInsert);
        }

        System.out.println(driveReportsForUpdate);

        if (!driveReportsForUpdate.isEmpty()) {
            step9Mapper.updateDriveReports(driveReportsForUpdate);
        }

        List<Integer> trashIds = driveIds.stream().toList();

        if (!trashIds.isEmpty()) {
            step9Mapper.updateDriveReportsGroupReportIdFk(trashIds);
        }
    }

    public ResponseDTO<List<GroupDriveReportDTO>> findGroupDriveReportList(GroupDriveReportDTO groupReport) {
        groupReport.setGroupDriverIdFk(getDriverInfo().getDriverId());
        return new ResponseDTO<>(200, step9Mapper.selectGroupDriveReportList(groupReport));
    }

    public ResponseDTO<GroupDriveReportDTO> findGroupDriveReport(GroupDriveReportDTO groupReport) {
        groupReport.setGroupDriverIdFk(getDriverInfo().getDriverId());
        groupReport = step9Mapper.selectGroupDriveReport(groupReport);
        groupReport.setDriveReports(step9Mapper.selectDriveReportsForGroupDTO(groupReport));

        return new ResponseDTO<>(200, groupReport);
    }

    public ResponseDTO<List<DriveReportDTO>> findDriveReportList(DriveReportDTO driveReport) {
        driveReport.setDriverIdFk(getDriverInfo().getDriverId());
        return new ResponseDTO<>(200, step9Mapper.selectDriveReportList(driveReport));
    }

    public ResponseDTO<DriveReportDTO> findDriveReport(DriveReportDTO driveReport) {
        driveReport.setDriverIdFk(getDriverInfo().getDriverId());
        return new ResponseDTO<>(200, step9Mapper.selectDriveReport(driveReport));
    }

    public ResponseDTO<String> removeGroupDriveReport(GroupDriveReportDTO groupReport) {
        groupReport.setGroupWriterIdFk(getUserIdFk());

        step9Mapper.deleteGroupDriveReport(groupReport);
        step9Mapper.updateAllGroupReportIdFk(groupReport.getGroupReportId());

        return new ResponseDTO<String>(200, "삭제 완료.");
    }

    public ResponseDTO<List<SubmitterDTO>> findSubmitterList() {
        return new ResponseDTO<>(200, step9Mapper.selectSubmitterList());
    }

    public void uploadFileByGroupReportId(MultipartFile file, int groupReportId) {
        fileUtil.uploadFile(file, groupReportId);
    }
}
