package com.scyllacore.dumpWeb.manageModule.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage/step1")
@RequiredArgsConstructor
public class Step1ForDriverMenuController {

    @GetMapping(value = "")
    public String step1() {
        return "/manage/step1/step1_index";
    }
}
