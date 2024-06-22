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

    @PostMapping(value = "/fetch/groupOrderReportSave")
    @ResponseBody
    public ResponseEntity<ResponseDTO<String>> groupOrderReportSave(
            @RequestPart(value = "dto") GroupDriveReportDTO.Request groupReport,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        return step11Service.saveGroupOrderReport(groupReport, imageFile);
    }

    @PostMapping(value = "/fetch/groupOrderReportList")
    @ResponseBody
    public ResponseEntity<List<GroupDriveReportDTO.Response>> groupOrderReportList(@RequestBody GroupDriveReportDTO.Request groupReport) {
        return step11Service.findGroupOrderReportList(groupReport);
    }

    @PostMapping(value = "/fetch/groupOrderReportDetails")
    @ResponseBody
    public ResponseEntity<GroupDriveReportDTO.Response> groupOrderReportDetails(@RequestBody GroupDriveReportDTO.Request groupReport) {
        return step11Service.findGroupOrderReport(groupReport);
    }

    @DeleteMapping(value = "/fetch/groupOrderReportRemove")
    @ResponseBody
    public ResponseEntity<ResponseDTO<String>> groupOrderReportRemove(@RequestBody GroupDriveReportDTO.Request groupReport) {
        return step11Service.removeGroupOrderReport(groupReport);
    }

    @PostMapping(value = "/fetch/orderReportList")
    @ResponseBody
    public ResponseEntity<List<DriveReportDTO.Response>> orderReportList(@RequestBody DriveReportDTO.Request driveReport) {
        return step11Service.findOrderReportList(driveReport);
    }

    @PostMapping(value = "/fetch/orderReportDetails")
    @ResponseBody
    public ResponseEntity<DriveReportDTO.Response> orderReportDetails(@RequestBody DriveReportDTO.Request driveReport) {
        return step11Service.findOrderReport(driveReport);
    }

    @GetMapping(value = "/fetch/driverList")
    @ResponseBody
    public ResponseEntity<List<UserDetailDTO.Driver>> submitterList() {
        return step11Service.findDriverList();
    }

}
