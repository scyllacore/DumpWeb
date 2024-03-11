package com.scyllacore.dumpWeb.commonModule.controller;

import com.scyllacore.dumpWeb.commonModule.util.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class StepController {

    private final FileUtil fileUtil;

    @GetMapping("/manage/driver")
    public String driverPage() {
        return "redirect:/manage/step1";
    }

    @GetMapping("/manage/submitter")
    public String submitterPage() {
        return "redirect:/manage/step2";
    }

    @RequestMapping(value = "/image/{fileId}", method = RequestMethod.GET)
    public void getImage(HttpServletResponse response, @PathVariable Long fileId) throws Exception {
        fileUtil.getImageFile(response, fileId);
    }

}
