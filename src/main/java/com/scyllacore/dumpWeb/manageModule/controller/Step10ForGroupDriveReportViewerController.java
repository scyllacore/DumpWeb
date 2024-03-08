package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportSearchOptionDTO;
import org.springframework.http.ResponseEntity;
import com.scyllacore.dumpWeb.manageModule.service.Step10ForGroupDriveReportViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step10")
@RequiredArgsConstructor
public class Step10ForGroupDriveReportViewerController {

    private final Step10ForGroupDriveReportViewerService step10Service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String step10() {
        return "/manage/step10/step10_index";
    }

    @GetMapping(value = "/fetch/recommendKeywordList")
    @ResponseBody
    public ResponseEntity<GroupDriveReportSearchOptionDTO> recommendKeywordList() {
        return step10Service.findRecommendKeywordList();
    }

    @PostMapping(value = "/fetch/groupDriveReportList")
    @ResponseBody
    public ResponseEntity<List<GroupDriveReportSearchOptionDTO>> groupDriveReportList(@RequestBody GroupDriveReportSearchOptionDTO option) {
        return step10Service.findGroupDriveReportListByOption(option);
    }

    @PutMapping(value = "/fetch/paymentInBulk")
    @ResponseBody
    public ResponseEntity<String> paymentInBulk(@RequestBody GroupDriveReportSearchOptionDTO option) {
        return step10Service.modifyPaymentInBulk(option);
    }

}
