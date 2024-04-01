package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import jakarta.validation.Valid;
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
    public ResponseEntity<ResponseDTO<String>> driveReportSave(@Valid @RequestBody DriveReportDTO.Request driveReport){
        return step3Service.saveDriveReport(driveReport);
    }

    @PostMapping(value = "/fetch/driveReportList")
    @ResponseBody
    public ResponseEntity<List<DriveReportDTO.Response>> driveReportList(@RequestBody DriveReportDTO.Request driveReport){
        return step3Service.findDriveReportList(driveReport);
    }

    @PostMapping(value = "/fetch/driveReportDetails")
    @ResponseBody
    public ResponseEntity<DriveReportDTO.Response> driveReportDetails(@RequestBody DriveReportDTO.Request driveReport){
        return step3Service.findDriveReport(driveReport);
    }

    @DeleteMapping(value = "/fetch/driveReportRemove")
    @ResponseBody
    public ResponseEntity<ResponseDTO<String>> driveReportRemove(@RequestBody DriveReportDTO.Request driveReport){
        return step3Service.removeDriveReport(driveReport);
    }

    @GetMapping(value = "/fetch/submitterList")
    @ResponseBody
    public ResponseEntity<List<UserDetailDTO.Submitter>> submitterList(){
        return step3Service.findSubmitterList();
    }
}
