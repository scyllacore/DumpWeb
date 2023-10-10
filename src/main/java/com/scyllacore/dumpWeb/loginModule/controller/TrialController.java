package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.loginModule.service.TrialService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/trial")
@RequiredArgsConstructor
public class TrialController {

    private final TrialService trialService;

    @GetMapping("")
    public String trialPage() {
        return "/login/trial";
    }

    @PostMapping("/ajax/trialLogin")
    @ResponseBody
    public ResponseDTO<String> trialLogin(HttpServletRequest request, LoginDTO loginInfo) {
        ResponseDTO<String> trialLoginType = trialService.loginForTrial(request, loginInfo);

        trialLoginType.setData("/manage/" + trialLoginType.getData());
        return trialLoginType;
    }
}
