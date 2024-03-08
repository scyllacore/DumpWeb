package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.LoginMapper;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.TrialMapper;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrialService {

    private final TrialMapper trialMapper;
    private final LoginMapper loginMapper;

    public ResponseEntity<ResponseDTO<String>> loginForTrial(AuthDTO trialLoginInfo, HttpServletRequest request) {
        setUserType(trialLoginInfo);
        trialLoginInfo = trialMapper.selectTrialUserInfo(trialLoginInfo);

        HttpSession session = request.getSession();
        setSessionUserType(trialLoginInfo, session);
        session.setAttribute("loginInfo", trialLoginInfo);

        return ResponseEntity.ok(new ResponseDTO<>("/manage/" + trialLoginInfo.getUserType()));
    }

    private void setUserType(AuthDTO trialLoginInfo) {
        if (trialLoginInfo.getUserType().equals("driver")) {
            trialLoginInfo.setUserIdIdx(1);
        } else {
            trialLoginInfo.setUserIdIdx(2);
        }
    }

    private void setSessionUserType(AuthDTO login, HttpSession session) {
        if (login.getUserType().equals("driver")) {
            DriverDTO driver = loginMapper.selectDriverInfo(login);
            session.setAttribute("driverInfo", driver);
        } else {
            SubmitterDTO submitter = loginMapper.selectSubmitterInfo(login);
            session.setAttribute("submitterInfo", submitter);
        }
    }

}
