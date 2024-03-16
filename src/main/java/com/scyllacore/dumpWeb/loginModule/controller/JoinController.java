package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.loginModule.service.JoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


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
    public ResponseEntity<ResponseDTO<String>> join(@Valid @RequestBody AuthDTO.Request joinInfo) throws Exception {
        return joinService.join(joinInfo);
    }
}
