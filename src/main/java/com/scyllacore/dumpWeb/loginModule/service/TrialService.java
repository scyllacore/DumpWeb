package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.LoginMapper;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.TrialMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrialService {

    private final TrialMapper trialMapper;
    private final LoginMapper loginMapper;

    public ResponseDTO<String> loginForTrial(HttpServletRequest request, AuthDTO trialLoginInfo) {

        if (trialLoginInfo.getUserType().equals("driver")) {
            trialLoginInfo.setUserIdIdx(1);
        } else {
            trialLoginInfo.setUserIdIdx(2);
        }

        trialLoginInfo = trialMapper.selectTrialUserInfo(trialLoginInfo);

        HttpSession session = request.getSession();

        if (trialLoginInfo.getUserType().equals("driver")) {
            DriverDTO driver =  loginMapper.selectDriverInfo(trialLoginInfo);
            trialLoginInfo.setProfileName(driver.getCarNo());
            session.setAttribute("driverInfo", driver);

        } else {
            SubmitterDTO submitter = loginMapper.selectSubmitterInfo(trialLoginInfo);
            trialLoginInfo.setProfileName(submitter.getTel());
            session.setAttribute("submitterInfo", submitter);

        }

        session.setAttribute("loginInfo", trialLoginInfo);

        return new ResponseDTO<String>(200, trialLoginInfo.getUserType());
    }
}
