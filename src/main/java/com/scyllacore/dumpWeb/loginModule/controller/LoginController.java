package com.scyllacore.dumpWeb.loginModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
import com.scyllacore.dumpWeb.loginModule.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping(value = "")
    public String loginFormPage(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        return "/login/login_form";
    }

    @RequestMapping(value = "/ajax/loginTry", method = RequestMethod.POST)
    @ResponseBody
    public String loginTry(Login loginInfo, HttpServletRequest request) {
        String loginType = loginService.tryLogin(loginInfo, request);

        if (loginType.equals("driver")) {
            return "/dailyReport/driver";
        }
        return "/dailyReport/manager";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        loginService.logout(request);

        return "redirect:/login";
    }


}
