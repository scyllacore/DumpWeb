package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step7ForSubmitterOrderRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step7")
@RequiredArgsConstructor
public class Step7ForSubmitterOrderRegistrationController {
/*
    private final Step7ForSubmitterOrderRegistrationService step7Service;

    @GetMapping(value = "")
    public String step7() {
        return "/manage/step7/step7_index";
    }

    @PostMapping(value = "/fetch/orderSave")
    @ResponseBody
    public ResponseDTO<String> orderSave(@RequestBody DriveReportDTO driveReport) {
        return step7Service.saveOrder(driveReport);
    }

    @PostMapping(value = "/fetch/orderList")
    @ResponseBody
    public ResponseDTO<List<DriveReportDTO>> orderList(@RequestBody DriveReportDTO driveReport) {
        return step7Service.findOrderList(driveReport);
    }

    @DeleteMapping(value = "/fetch/orderRemove")
    @ResponseBody
    public ResponseDTO<String> orderRemove(@RequestBody DriveReportDTO driveReport) {
        return step7Service.removeOrder(driveReport);
    }

    @PostMapping(value = "/fetch/orderDetails")
    @ResponseBody
    public ResponseDTO<DriveReportDTO> orderDetails(@RequestBody DriveReportDTO driveReport) {
        return step7Service.findOrder(driveReport);
    }*/
}
