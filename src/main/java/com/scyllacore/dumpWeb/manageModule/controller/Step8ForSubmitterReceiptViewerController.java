package com.scyllacore.dumpWeb.manageModule.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manage/step8")
@RequiredArgsConstructor
public class Step8ForSubmitterReceiptViewerController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String step8() {
        return "/manage/step8/step8_index";
    }
}
