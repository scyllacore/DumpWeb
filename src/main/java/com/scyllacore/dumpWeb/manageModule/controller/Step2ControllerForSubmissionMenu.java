package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SearchOption;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.Summary;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.TSheetSub;
import com.scyllacore.dumpWeb.manageModule.service.Step2ServiceForSubmissionMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step2")
@RequiredArgsConstructor
public class Step2ControllerForSubmissionMenu {

    private final Step2ServiceForSubmissionMenu step2Service;

    @GetMapping(value = "")
    public String step2() {
        return "/manage/step2/step2_index";
    }

    @PostMapping(value = "/ajax/summary")
    @ResponseBody
    public Summary summary(SearchOption option) {
        return step2Service.summarize(option);
    }

    @GetMapping(value = "/ajax/todayDispatchStatus")
    @ResponseBody
    public List<TSheetSub> todayDispatchStatus() {
        return step2Service.findTodayDispatchStatus();
    }
}

