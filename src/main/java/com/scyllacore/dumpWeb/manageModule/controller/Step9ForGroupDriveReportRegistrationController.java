package com.scyllacore.dumpWeb.manageModule.controller;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step9ForGroupDriveReportRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseDTO<String> groupDriveReportSave(@RequestBody GroupDriveReportDTO groupReport) {
        return step9Service.saveGroupDriveReport(groupReport);
    }

    @PostMapping(value = "/fetch/groupDriveReportList")
    @ResponseBody
    public ResponseDTO<List<GroupDriveReportDTO>> groupDriveReportList(@RequestBody GroupDriveReportDTO groupReport) {
        System.out.println(groupReport);
        return step9Service.findGroupDriveReportList(groupReport);
    }

    @PostMapping(value = "/fetch/groupDriveReportDetails")
    @ResponseBody
    public ResponseDTO<GroupDriveReportDTO> groupDriveReportDetails(@RequestBody GroupDriveReportDTO groupReport) {
        return step9Service.findGroupDriveReport(groupReport);
    }

    @DeleteMapping(value = "/fetch/groupDriveReportRemove")
    @ResponseBody
    public ResponseDTO<String> groupDriveReportRemove(@RequestBody GroupDriveReportDTO groupReport) {
        return step9Service.removeGroupDriveReport(groupReport);
    }

    @PostMapping(value = "/fetch/driveReportList")
    @ResponseBody
    public ResponseDTO<List<DriveReportDTO>> driveReportList(@RequestBody DriveReportDTO driveReport) {
        return step9Service.findDriveReportList(driveReport);
    }

    @PostMapping(value = "/fetch/driveReportDetails")
    @ResponseBody
    public ResponseDTO<DriveReportDTO> driveReportDetails(@RequestBody DriveReportDTO driveReport) {
        return step9Service.findDriveReport(driveReport);
    }

    @GetMapping(value = "/fetch/submitterList")
    @ResponseBody
    public ResponseDTO<List<SubmitterDTO>> submitterList() {
        return step9Service.findSubmitterList();
    }

}
