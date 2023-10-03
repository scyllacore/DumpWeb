package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
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

    @PostMapping("/ajax/trialLoginTry")
    @ResponseBody
    public String trialLogin(HttpServletRequest request, Login login) {
        return trialService.loginForTrial(request, login);
    }
}
