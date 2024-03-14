package com.scyllacore.dumpWeb.manageModule.controller;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step9ForGroupDriveReportRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/manage/step9")
@RequiredArgsConstructor
public class Step9ForGroupDriveReportRegistrationController {

    private final Step9ForGroupDriveReportRegistrationService step9Service;

    @GetMapping(value = "")
    public String step9() {
        return "/manage/step9/step9_index";
    }

    @PostMapping(value = "/fetch/groupDriveReportSave")
    @ResponseBody
    public ResponseEntity<String> groupDriveReportSave(
            @Valid @RequestPart(value = "dto") GroupDriveReportDTO.Request groupReport,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        return step9Service.saveGroupDriveReport(groupReport, imageFile);
    }

    @PostMapping(value = "/fetch/groupDriveReportList")
    @ResponseBody
    public ResponseEntity<List<GroupDriveReportDTO.Response>> groupDriveReportList(@RequestBody GroupDriveReportDTO.Request groupReport) {
        return step9Service.findGroupDriveReportList(groupReport);
    }

    @PostMapping(value = "/fetch/groupDriveReportDetails")
    @ResponseBody
    public ResponseEntity<GroupDriveReportDTO.Response> groupDriveReportDetails(@RequestBody GroupDriveReportDTO.Request groupReport) {
        return step9Service.findGroupDriveReport(groupReport);
    }

    @DeleteMapping(value = "/fetch/groupDriveReportRemove")
    @ResponseBody
    public ResponseEntity<String> groupDriveReportRemove(@RequestBody GroupDriveReportDTO.Request groupReport) {
        return step9Service.removeGroupDriveReport(groupReport);
    }

    @PostMapping(value = "/fetch/driveReportList")
    @ResponseBody
    public ResponseEntity<List<DriveReportDTO.Response>> driveReportList(@RequestBody DriveReportDTO.Request driveReport) {
        return step9Service.findDriveReportList(driveReport);
    }

    @PostMapping(value = "/fetch/driveReportDetails")
    @ResponseBody
    public ResponseEntity<DriveReportDTO.Response> driveReportDetails(@RequestBody DriveReportDTO.Request driveReport) {
        return step9Service.findDriveReport(driveReport);
    }

    @GetMapping(value = "/fetch/submitterList")
    @ResponseBody
    public ResponseEntity<List<UserDetailDTO.Submitter>> submitterList() {
        return step9Service.findSubmitterList();
    }

}
