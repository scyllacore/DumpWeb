package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.LoginMapper;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.TrialMapper;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.SessionUtil;
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

    private final SessionUtil sessionUtil;

    public ResponseEntity<ResponseDTO<String>> loginForTrial(AuthDTO.Trial trial) {
        setUserType(trial);
        AuthDTO.Request trialLoginInfo = trialMapper.selectTrialUserInfo(trial);

        if (trialLoginInfo.getUserIdIdx() == null) {
            throw new NullPointerException();
        }

        HttpSession session = sessionUtil.getSession(true);

        if (setSessionUserType(trialLoginInfo, session) == null) {
            throw new NullPointerException();
        }
        session.setAttribute("loginInfo", trialLoginInfo);

        return ResponseEntity.ok(new ResponseDTO<>("/manage/" + trial.getUserType()));
    }

    private void setUserType(AuthDTO.Trial trial) {
        if (trial.getUserType().equals("driver")) {
            trial.setUserIdIdx(1L);
        } else {
            trial.setUserIdIdx(2L);
        }
    }

    private Long setSessionUserType(AuthDTO.Request login, HttpSession session) {
        if (login.getUserType().equals("driver")) {
            UserDetailDTO.Driver driver = loginMapper.selectDriverInfo(login);
            session.setAttribute("driverInfo", driver);
            return driver.getUserIdFk();
        }

        UserDetailDTO.Submitter submitter = loginMapper.selectSubmitterInfo(login);
        session.setAttribute("submitterInfo", submitter);
        return submitter.getUserIdFk();
    }

}
