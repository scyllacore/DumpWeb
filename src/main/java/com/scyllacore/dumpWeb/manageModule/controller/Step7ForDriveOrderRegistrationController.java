package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step7ForDriveOrderRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step7")
@RequiredArgsConstructor
public class Step7ForDriveOrderRegistrationController {

    private final Step7ForDriveOrderRegistrationService step7Service;

    @GetMapping(value = "")
    public String step7() {
        return "/manage/step7/step7_index";
    }

    @PostMapping(value = "/fetch/driveOrderSave")
    @ResponseBody
    public ResponseEntity<String> driveOrderSave(@Valid @RequestBody DriveReportDTO.Request driveReport) {
        return step7Service.saveDriveOrder(driveReport);
    }

    @PostMapping(value = "/fetch/driveOrderList")
    @ResponseBody
    public ResponseEntity<List<DriveReportDTO.Response>> driveOrderList(@RequestBody DriveReportDTO.Request driveReport) {
        return step7Service.findDriveOrderList(driveReport);
    }

    @PostMapping(value = "/fetch/driveOrderDetails")
    @ResponseBody
    public ResponseEntity<DriveReportDTO.Response> driveOrderDetails(@RequestBody DriveReportDTO.Request driveReport) {
        return step7Service.findDriveOrder(driveReport);
    }

    @DeleteMapping(value = "/fetch/driveOrderRemove")
    @ResponseBody
    public ResponseEntity<String> driveOrderRemove(@RequestBody DriveReportDTO.Request driveReport) {
        return step7Service.removeDriveOrder(driveReport);
    }

    @GetMapping(value = "/fetch/driverList")
    @ResponseBody
    public ResponseEntity<List<UserDetailDTO.Driver>> driverList() {
        return step7Service.findDriverList();
    }
}
