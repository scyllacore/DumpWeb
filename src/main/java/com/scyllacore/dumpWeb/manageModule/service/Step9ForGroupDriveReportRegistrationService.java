package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.constant.OperationStatus;
import com.scyllacore.dumpWeb.commonModule.constant.ResponseType;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step9ForGroupDriveReportRegistrationMapper;
import com.scyllacore.dumpWeb.commonModule.exception.RestApiException;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.FileUtil;
import com.scyllacore.dumpWeb.commonModule.util.SessionUtil;
import com.scyllacore.dumpWeb.manageModule.constants.Step9Flags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class Step9ForGroupDriveReportRegistrationService {
    private final SessionUtil sessionUtil;
    private final Step9ForGroupDriveReportRegistrationMapper step9Mapper;
    private final FileUtil fileUtil;

    @Transactional
    public ResponseEntity<ResponseDTO<String>> saveGroupDriveReport(GroupDriveReportDTO.Request groupReport
            , MultipartFile imageFile) throws IOException {

        System.out.println(groupReport);

        groupReport.setGroupWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());
        groupReport.setGroupDriverIdFk(sessionUtil.getDriverInfo().getDriverId());

        if (groupReport.getGroupReportId() == Step9Flags.NEW_GROUP_DRIVE_REPORT.getValue()) {
            insertGroupDriveReport(groupReport, imageFile);
        } else if (groupReport.getGroupUserType() == Step9Flags.ONLY_CHANGE_BY_DRIVER.getValue()) {
            updateGroupDriveReport(groupReport, imageFile);
        } else {
            updateGroupSubmit(groupReport);
        }

        return ResponseEntity.ok(new ResponseDTO<>("저장 완료."));
    }

    private void insertGroupDriveReport(GroupDriveReportDTO.Request newGroupReport
            , MultipartFile imageFile) throws IOException {

        if (step9Mapper.insertGroupDriveReport(newGroupReport) <= OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }

        uploadFile(newGroupReport, imageFile);

        List<DriveReportDTO.Request> DriveReports = newGroupReport.getDriveReports();
        for (int i = 0; i < newGroupReport.getDriveReports().size(); i++) {
            DriveReports.get(i).setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());
            DriveReports.get(i).setGroupReportIdFk(newGroupReport.getGroupReportId());
        }

        if (step9Mapper.insertDriveReports(newGroupReport.getDriveReports()) <= OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
    }

    private void uploadFile(GroupDriveReportDTO.Request newGroupReport, MultipartFile imageFile) throws IOException {
        if (imageFile == null) {
            return;
        }

        Long fileIdFk = fileUtil.uploadFile(imageFile, newGroupReport.getGroupReportId());

        if (fileIdFk == null) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }

        if (step9Mapper.updateFileIdFk(newGroupReport.getGroupReportId(), fileIdFk.longValue()) <= OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
    }

    public void updateGroupDriveReport(GroupDriveReportDTO.Request newGroupReport, MultipartFile imageFile) throws IOException {
        if (imageFile != null) {
            changeImage(newGroupReport, imageFile);
        }

        step9Mapper.updateGroupDriveReport(newGroupReport);

        Set<Long> driveIds = getOriginDriveReportIds(newGroupReport.getGroupReportId());
        List<DriveReportDTO.Request> newDriveReports = newGroupReport.getDriveReports();

        removeExistingDriveIds(driveIds, newDriveReports);

        List<DriveReportDTO.Request> driveReportsForInsert = new ArrayList<>();
        List<DriveReportDTO.Request> driveReportsForUpdate = new ArrayList<>();

        classifyAndPrepareDriveReports(
                newDriveReports, driveReportsForInsert, driveReportsForUpdate, newGroupReport.getGroupReportId());

        insertOrUpdateDriveReports(driveReportsForInsert, driveReportsForUpdate);
        updateDriveReportsIdFkToNull(driveIds.stream().toList());
    }

    void changeImage(GroupDriveReportDTO.Request newGroupReport, MultipartFile imageFile) throws IOException {
        deleteFile(newGroupReport.getFileIdFk());

        Long fileIdFk = fileUtil.uploadFile(imageFile, newGroupReport.getGroupReportId());
        step9Mapper.updateFileIdFk(newGroupReport.getGroupReportId(), fileIdFk.longValue());
    }

    private Set<Long> getOriginDriveReportIds(Long groupReportId) {
        return new HashSet<>(step9Mapper.selectDriveReportIdsByGroupReportId(groupReportId));
    }

    private void removeExistingDriveIds(Set<Long> driveIds, List<DriveReportDTO.Request> newDriveReports) {
        driveIds.removeAll(newDriveReports.stream()
                .map(DriveReportDTO.Request::getDriveReportId)
                .collect(Collectors.toList()));
    }

    private void classifyAndPrepareDriveReports(List<DriveReportDTO.Request> newDriveReports
            , List<DriveReportDTO.Request> driveReportsForInsert
            , List<DriveReportDTO.Request> driveReportsForUpdate
            , Long GroupReportId) {
        for (DriveReportDTO.Request driveReport : newDriveReports) {
            driveReport.setGroupReportIdFk(GroupReportId);
            if (driveReport.getDriveReportId() == Step9Flags.NEW_DRIVE_REPORT.getValue()) {
                driveReportsForInsert.add(driveReport);
            } else {
                driveReportsForUpdate.add(driveReport);
            }
        }
    }

    private void insertOrUpdateDriveReports(List<DriveReportDTO.Request> driveReportsForInsert
            , List<DriveReportDTO.Request> driveReportsForUpdate) {
        if (!driveReportsForInsert.isEmpty()) {
            step9Mapper.insertDriveReports(driveReportsForInsert);
        }
        if (!driveReportsForUpdate.isEmpty()) {
            step9Mapper.updateDriveReports(driveReportsForUpdate);
        }
    }

    private void updateDriveReportsIdFkToNull(List<Long> driveIds) {
        if (!driveIds.isEmpty()) {
            step9Mapper.updateDriveReportsIdFkToNull(driveIds);
        }
    }

    public void updateGroupSubmit(GroupDriveReportDTO.Request groupReport) {
        step9Mapper.updateGroupSubmit(groupReport);
        step9Mapper.updateReportsSubmit(groupReport.getDriveReports());
    }

    @Transactional
    public ResponseEntity<ResponseDTO<String>> removeGroupDriveReport(GroupDriveReportDTO.Request groupReport) {
        groupReport.setGroupWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());

        step9Mapper.deleteGroupDriveReport(groupReport);
        step9Mapper.updateAllGroupReportIdFk(groupReport.getGroupReportId());

        return ResponseEntity.ok(new ResponseDTO<>("삭제 완료."));
    }

    public void deleteFile(Long fileIdFk) {
        fileUtil.deleteFile(fileIdFk);
    }

    public ResponseEntity<List<GroupDriveReportDTO.Response>> findGroupDriveReportList(GroupDriveReportDTO.Request groupReport) {
        groupReport.setGroupDriverIdFk(sessionUtil.getDriverInfo().getDriverId());
        return ResponseEntity.ok(step9Mapper.selectGroupDriveReportList(groupReport));
    }

    public ResponseEntity<GroupDriveReportDTO.Response> findGroupDriveReport(GroupDriveReportDTO.Request groupReport) {
        groupReport.setGroupDriverIdFk(sessionUtil.getDriverInfo().getDriverId());
        GroupDriveReportDTO.Response response = step9Mapper.selectGroupDriveReport(groupReport);
        response.setDriveReports(step9Mapper.selectDriveReportsForGroupDTO(groupReport));

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<DriveReportDTO.Response>> findDriveReportList(DriveReportDTO.Request driveReport) {
        driveReport.setDriverIdFk(sessionUtil.getDriverInfo().getDriverId());
        return ResponseEntity.ok(step9Mapper.selectDriveReportList(driveReport));
    }

    public ResponseEntity<DriveReportDTO.Response> findDriveReport(DriveReportDTO.Request driveReport) {
        driveReport.setDriverIdFk(sessionUtil.getDriverInfo().getDriverId());
        return ResponseEntity.ok(step9Mapper.selectDriveReport(driveReport));
    }


    public ResponseEntity<List<UserDetailDTO.Submitter>> findSubmitterList() {
        return ResponseEntity.ok(step9Mapper.selectSubmitterList());
    }
}