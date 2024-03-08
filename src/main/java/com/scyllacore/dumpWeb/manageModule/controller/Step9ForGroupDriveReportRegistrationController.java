package com.scyllacore.dumpWeb.manageModule.controller;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import org.springframework.http.ResponseEntity;
import com.scyllacore.dumpWeb.manageModule.service.Step9ForGroupDriveReportRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            @RequestPart(value = "dto") GroupDriveReportDTO groupReport,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        return step9Service.saveGroupDriveReport(groupReport,imageFile);
    }

    @PostMapping(value = "/fetch/groupDriveReportList")
    @ResponseBody
    public ResponseEntity<List<GroupDriveReportDTO>> groupDriveReportList(@RequestBody GroupDriveReportDTO groupReport) {
        System.out.println(groupReport);
        return step9Service.findGroupDriveReportList(groupReport);
    }

    @PostMapping(value = "/fetch/groupDriveReportDetails")
    @ResponseBody
    public ResponseEntity<GroupDriveReportDTO> groupDriveReportDetails(@RequestBody GroupDriveReportDTO groupReport) {
        return step9Service.findGroupDriveReport(groupReport);
    }

    @DeleteMapping(value = "/fetch/groupDriveReportRemove")
    @ResponseBody
    public ResponseEntity<String> groupDriveReportRemove(@RequestBody GroupDriveReportDTO groupReport) {
        return step9Service.removeGroupDriveReport(groupReport);
    }

    @PostMapping(value = "/fetch/driveReportList")
    @ResponseBody
    public ResponseEntity<List<DriveReportDTO>> driveReportList(@RequestBody DriveReportDTO driveReport) {
        return step9Service.findDriveReportList(driveReport);
    }

    @PostMapping(value = "/fetch/driveReportDetails")
    @ResponseBody
    public ResponseEntity<DriveReportDTO> driveReportDetails(@RequestBody DriveReportDTO driveReport) {
        return step9Service.findDriveReport(driveReport);
    }

    @GetMapping(value = "/fetch/submitterList")
    @ResponseBody
    public ResponseEntity<List<SubmitterDTO>> submitterList() {
        return step9Service.findSubmitterList();
    }

}
