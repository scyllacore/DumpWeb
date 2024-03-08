package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import org.springframework.http.ResponseEntity;
import com.scyllacore.dumpWeb.manageModule.service.Step3ForDriveReportRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step3")
@RequiredArgsConstructor
public class Step3ForDriveReportRegistrationController {

    private final Step3ForDriveReportRegistrationService step3Service;

    @GetMapping(value = "")
    public String step3() {
        return "/manage/step3/step3_index";
    }

    @PostMapping(value = "/fetch/driveReportSave")
    @ResponseBody
    public ResponseEntity<String> driveReportSave(@RequestBody DriveReportDTO driveReport){
        return step3Service.saveDriveReport(driveReport);
    }

    @PostMapping(value = "/fetch/driveReportList")
    @ResponseBody
    public ResponseEntity<List<DriveReportDTO>> driveReportList(@RequestBody DriveReportDTO driveReport){
        return step3Service.findDriveReportList(driveReport);
    }

    @PostMapping(value = "/fetch/driveReportDetails")
    @ResponseBody
    public ResponseEntity<DriveReportDTO> driveReportDetails(@RequestBody DriveReportDTO driveReport){
        return step3Service.findDriveReport(driveReport);
    }

    @DeleteMapping(value = "/fetch/driveReportRemove")
    @ResponseBody
    public ResponseEntity<String> driveReportRemove(@RequestBody DriveReportDTO driveReport){
        return step3Service.removeDriveReport(driveReport);
    }

    @GetMapping(value = "/fetch/submitterList")
    @ResponseBody
    public ResponseEntity<List<SubmitterDTO>> submitterList(){
        return step3Service.findSubmitterList();
    }
}
