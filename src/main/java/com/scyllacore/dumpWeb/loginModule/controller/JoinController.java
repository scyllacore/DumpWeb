package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.loginModule.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join/typeSelect")
    public String joinTypeSelect() {
        return "/auth/join_type";
    }

    @GetMapping("/join/driver")
    public String driverJoinForm() {
        return "/auth/driver_join_form";
    }

    @GetMapping("/join/submitter")
    public String submitterJoinForm() {
        return "/auth/submitter_join_form";
    }

    @PostMapping(value = "/auth/fetch/join")
    @ResponseBody
    public ResponseDTO<String> join(@RequestBody AuthDTO joinInfo) {
        return joinService.join(joinInfo);
    }
}
