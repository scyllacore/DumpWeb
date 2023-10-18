package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.loginModule.service.LoginService;
import com.scyllacore.dumpWeb.loginModule.service.TrialService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TrialController {

    private final LoginService loginService;
    private final TrialService trialService;

    @GetMapping("/trial")
    public String trialPage() {
        return "/auth/trial";
    }

    @PostMapping("/auth/fetch/trialLogin")
    @ResponseBody
    public ResponseDTO<String> trialLogin(@RequestBody AuthDTO loginInfo, HttpServletRequest request) {

        loginService.logout(request);

        ResponseDTO<String> trialLoginType = trialService.loginForTrial(request, loginInfo);

        trialLoginType.setData("/manage/" + trialLoginType.getData());
        return trialLoginType;
    }
}
