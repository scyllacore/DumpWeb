package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.LoginMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginMapper loginMapper;

    public ResponseDTO<String> login(AuthDTO loginInfo, HttpServletRequest request) {

        AuthDTO login = loginMapper.selectUserInfoForIdValidCheck(loginInfo);

        if (login == null) {
            return new ResponseDTO<String>(403, "등록되지 않은 ID 입니다.");
        }

        login = loginMapper.selectUserInfoForPwdValidCheck(loginInfo);

        if (login == null) {
            return new ResponseDTO<String>(403, "비밀번호가 일치하지 않습니다.");
        }


        HttpSession session = request.getSession();

        if (login.getUserType().equals("driver")) {
            DriverDTO driver =  loginMapper.selectDriverInfo(login);
            login.setProfileName(driver.getCarNo());
            session.setAttribute("driverInfo", driver);
        } else {
            SubmitterDTO submitter = loginMapper.selectSubmitterInfo(login);
            login.setProfileName(submitter.getTel());
            session.setAttribute("submitterInfo", submitter);
        }

        session.setAttribute("loginInfo", login);

        return new ResponseDTO<String>(200, login.getUserType());
    }


    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }

}
