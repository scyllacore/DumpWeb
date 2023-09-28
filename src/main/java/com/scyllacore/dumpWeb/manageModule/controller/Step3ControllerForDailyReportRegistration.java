package com.scyllacore.dumpWeb.manageModule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manage/step3")
@RequiredArgsConstructor
public class Step3ControllerForDailyReportRegistration {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String step3() {
        return "/manage/step3/step3_index";
    }

}
