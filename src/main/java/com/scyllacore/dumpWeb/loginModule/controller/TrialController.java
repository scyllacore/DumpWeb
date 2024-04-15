package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.loginModule.service.LoginService;
import com.scyllacore.dumpWeb.loginModule.service.TrialService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResponseEntity<ResponseDTO<String>> trialLogin(@RequestBody AuthDTO.Trial trial) {
        loginService.logout();
        return trialService.loginForTrial(trial);
    }
}
