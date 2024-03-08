package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import org.springframework.http.ResponseEntity;
import com.scyllacore.dumpWeb.manageModule.service.Step7ForSubmitterOrderRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step7")
@RequiredArgsConstructor
public class Step7ForSubmitterOrderRegistrationController {

    private final Step7ForSubmitterOrderRegistrationService step7Service;

    @GetMapping(value = "")
    public String step7() {
        return "/manage/step7/step7_index";
    }

    @PostMapping(value = "/fetch/driveReportSave")
    @ResponseBody
    public ResponseEntity<String> driveOrderSave(@RequestBody DriveReportDTO driveReport) {
        return step7Service.saveDriveOrder(driveReport);
    }

    @PostMapping(value = "/fetch/driveReportList")
    @ResponseBody
    public ResponseEntity<List<DriveReportDTO>> driveReportList(@RequestBody DriveReportDTO driveReport) {
        return step7Service.findDriveOrderList(driveReport);
    }

    @PostMapping(value = "/fetch/driveReportDetails")
    @ResponseBody
    public ResponseEntity<DriveReportDTO> driveOrderDetails(@RequestBody DriveReportDTO driveReport) {
        return step7Service.findDriveOrder(driveReport);
    }

    @DeleteMapping(value = "/fetch/driveReportRemove")
    @ResponseBody
    public ResponseEntity<String> driveOrderRemove(@RequestBody DriveReportDTO driveReport) {
        return step7Service.removeDriveOrder(driveReport);
    }

    @GetMapping(value = "/fetch/driverList")
    @ResponseBody
    public ResponseEntity<List<DriverDTO>> submitterList() {
        return step7Service.findDriverList();
    }
}
