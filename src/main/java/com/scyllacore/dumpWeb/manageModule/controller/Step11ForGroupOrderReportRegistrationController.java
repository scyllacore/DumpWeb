package com.scyllacore.dumpWeb.manageModule.controller;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step11ForGroupOrderReportRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/manage/step11")
@RequiredArgsConstructor
public class Step11ForGroupOrderReportRegistrationController {

    private final Step11ForGroupOrderReportRegistrationService step11Service;

    @GetMapping(value = "")
    public String step11() {
        return "/manage/step11/step11_index";
    }

    @PostMapping(value = "/fetch/groupDriveReportSave")
    @ResponseBody
    public ResponseEntity<ResponseDTO<String>> groupDriveReportSave(
            @Valid @RequestPart(value = "dto") GroupDriveReportDTO.Request groupReport,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        return step11Service.saveGroupDriveReport(groupReport, imageFile);
    }

    @PostMapping(value = "/fetch/groupDriveReportList")
    @ResponseBody
    public ResponseEntity<List<GroupDriveReportDTO.Response>> groupDriveReportList(@RequestBody GroupDriveReportDTO.Request groupReport) {
        return step11Service.findGroupDriveReportList(groupReport);
    }

    @PostMapping(value = "/fetch/groupDriveReportDetails")
    @ResponseBody
    public ResponseEntity<GroupDriveReportDTO.Response> groupDriveReportDetails(@RequestBody GroupDriveReportDTO.Request groupReport) {
        return step11Service.findGroupDriveReport(groupReport);
    }

    @DeleteMapping(value = "/fetch/groupDriveReportRemove")
    @ResponseBody
    public ResponseEntity<ResponseDTO<String>> groupDriveReportRemove(@RequestBody GroupDriveReportDTO.Request groupReport) {
        return step11Service.removeGroupDriveReport(groupReport);
    }

    @PostMapping(value = "/fetch/driveReportList")
    @ResponseBody
    public ResponseEntity<List<DriveReportDTO.Response>> driveReportList(@RequestBody DriveReportDTO.Request driveReport) {
        return step11Service.findDriveReportList(driveReport);
    }

    @PostMapping(value = "/fetch/driveReportDetails")
    @ResponseBody
    public ResponseEntity<DriveReportDTO.Response> driveReportDetails(@RequestBody DriveReportDTO.Request driveReport) {
        return step11Service.findDriveReport(driveReport);
    }

    @GetMapping(value = "/fetch/driverList")
    @ResponseBody
    public ResponseEntity<List<UserDetailDTO.Driver>> driverList() {
        return step11Service.findDriverList();
    }
}
