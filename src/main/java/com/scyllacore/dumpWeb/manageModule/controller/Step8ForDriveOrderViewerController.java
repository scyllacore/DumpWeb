package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step8ForDriveOrderViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step8")
@RequiredArgsConstructor
public class Step8ForDriveOrderViewerController {

    private final Step8ForDriveOrderViewerService step8Service;

    @GetMapping(value = "")
    public String step8() {
        return "/manage/step8/step8_index";
    }

    @GetMapping(value = "/fetch/recommendKeywordList")
    @ResponseBody
    public ResponseEntity<DriveReportSearchOptionDTO.Response> recommendKeywordList() {
        return step8Service.findRecommendKeywordList();
    }

    @PostMapping(value = "/fetch/driveOrderList")
    @ResponseBody
    public ResponseEntity<List<DriveReportDTO.Response>> driveOrderList(@RequestBody DriveReportSearchOptionDTO.Request option) {
        return step8Service.findDriveOrderListByOption(option);
    }

    @PutMapping(value = "/fetch/paymentInBulk")
    @ResponseBody
    public ResponseEntity<ResponseDTO<String>> paymentInBulk(@RequestBody DriveReportSearchOptionDTO.Request option) {
        return step8Service.modifyPaymentInBulk(option);
    }
}
