package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.login.TrialMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrialService {

    private final TrialMapper trialMapper;

    public ResponseDTO<String> loginForTrial(HttpServletRequest request, LoginDTO trialLoginInfo) {

        if (trialLoginInfo.getUserType().equals("driver")) {
            trialLoginInfo.setUserId("08호7313");
        } else {
            trialLoginInfo.setUserId("010-3717-7406");
        }

        trialLoginInfo = trialMapper.selectTrialUserInfo(trialLoginInfo);

        trialLoginInfo.setTrialChk(true);
        HttpSession session = request.getSession();
        session.setAttribute("loginInfo", trialLoginInfo);

        return new ResponseDTO<String>(200, trialLoginInfo.getUserType());
    }
}
