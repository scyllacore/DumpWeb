package com.scyllacore.dumpWeb.dailyReportModule.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manage/step1")
@RequiredArgsConstructor
public class Step1ControllerForDriverMenu {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String step1() {
        return "/manage/step1/step1_index";
    }
}
