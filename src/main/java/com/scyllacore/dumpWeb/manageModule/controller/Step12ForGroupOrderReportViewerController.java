package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step10ForGroupDriveReportViewerService;
import com.scyllacore.dumpWeb.manageModule.service.Step12ForGroupOrderReportViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step12")
@RequiredArgsConstructor
public class Step12ForGroupOrderReportViewerController {

    private final Step12ForGroupOrderReportViewerService step12Service;

    @GetMapping(value = "")
    public String step12() {
        return "/manage/step12/step12_index";
    }

    @GetMapping(value = "/fetch/recommendKeywordList")
    @ResponseBody
    public ResponseEntity<GroupDriveReportSearchOptionDTO.Response> recommendKeywordList() {
        return step12Service.findRecommendKeywordList();
    }

    @PostMapping(value = "/fetch/groupOrderReportList")
    @ResponseBody
    public ResponseEntity<List<GroupDriveReportDTO.Response>> groupOrderReportList(@RequestBody GroupDriveReportSearchOptionDTO.Request option) {
        return step12Service.findGroupOrderReportListByOption(option);
    }

    @PutMapping(value = "/fetch/paymentInBulk")
    @ResponseBody
    public ResponseEntity<ResponseDTO<String>> paymentInBulk(@RequestBody GroupDriveReportSearchOptionDTO.Request option) {
        return step12Service.modifyPaymentInBulk(option);
    }

}
