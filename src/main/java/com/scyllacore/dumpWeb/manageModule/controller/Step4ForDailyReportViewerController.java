package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step4ForDailyReportViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step4")
@RequiredArgsConstructor
public class Step4ForDailyReportViewerController {

    private final Step4ForDailyReportViewerService step4Service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String step4() {
        return "/manage/step4/step4_index";
    }

    @GetMapping(value="/fetch/recommendKeywordList")
    @ResponseBody
    public ResponseDTO<DriveReportSearchOptionDTO> recommendKeywordList(){
        return step4Service.findRecommendKeywordList();
    }

    @PostMapping(value = "/fetch/driveReportList")
    @ResponseBody
    public ResponseDTO<List<DriveReportSearchOptionDTO>> driveReportList(@RequestBody DriveReportSearchOptionDTO option) {
        return step4Service.findDriveReportListByOption(option);
    }

    @PutMapping(value = "/fetch/paymentInBulk")
    @ResponseBody
    public ResponseDTO<String> paymentInBulk(@RequestBody DriveReportSearchOptionDTO option) {
        return step4Service.modifyPaymentInBulk(option);
    }

}
