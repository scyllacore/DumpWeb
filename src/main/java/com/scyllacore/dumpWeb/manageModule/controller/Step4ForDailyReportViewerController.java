package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step4ForDailyReportViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage/step4")
@RequiredArgsConstructor
public class Step4ForDailyReportViewerController {

    private final Step4ForDailyReportViewerService step4Service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String step4() {
        return "/manage/step4/step4_index";
    }

    @GetMapping(value="/fetch/recommendKeyword")
    @ResponseBody
    public ResponseDTO<DriveReportSearchOptionDTO> recommendKeyword(){
        return step4Service.findRecommendKeyword();
    }

}
