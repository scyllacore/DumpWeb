package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step8ForSubmitterReceiptViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step8")
@RequiredArgsConstructor
public class Step8ForSubmitterReceiptViewerController {

    private final Step8ForSubmitterReceiptViewerService step8Service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String step8() {
        return "/manage/step8/step8_index";
    }

    @GetMapping(value = "/fetch/recommendKeywordList")
    @ResponseBody
    public ResponseDTO<DriveReportSearchOptionDTO> recommendKeywordList() {
        return step8Service.findRecommendKeywordList();
    }

    @PostMapping(value = "/fetch/driveReportList")
    @ResponseBody
    public ResponseDTO<List<DriveReportSearchOptionDTO>> driveReportList(@RequestBody DriveReportSearchOptionDTO option) {
        return step8Service.findDriveReportListByOption(option);
    }

    @PutMapping(value = "/fetch/paymentInBulk")
    @ResponseBody
    public ResponseDTO<String> paymentInBulk(@RequestBody DriveReportSearchOptionDTO option) {
        return step8Service.modifyPaymentInBulk(option);
    }

}
