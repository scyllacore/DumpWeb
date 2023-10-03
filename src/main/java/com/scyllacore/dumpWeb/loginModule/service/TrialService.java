package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.login.TrialMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrialService {

    private final TrialMapper trialMapper;

    public String loginForTrial(HttpServletRequest request, LoginDTO login) {

        if (login.getUserType().equals("driver")) {
            login.setUserId("08호7313");
        } else {
            login.setUserId("010-3717-7406");
        }

        LoginDTO trialLoginInfo = trialMapper.selectTrialUserInfo(login);

        if (trialLoginInfo != null) {
            trialLoginInfo.setTrialChk(true);
            HttpSession session = request.getSession();
            session.setAttribute("loginInfo", trialLoginInfo);
        }

        return "체험판으로 접속했습니다.";
    }
}
