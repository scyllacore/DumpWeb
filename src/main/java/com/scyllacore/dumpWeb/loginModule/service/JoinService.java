package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
import com.scyllacore.dumpWeb.commonModule.db.mapper.login.JoinMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final JoinMapper joinMapper;

    public String join(Login loginInfo) {

        if (loginInfo.getUserPosition().equals("manager")) {
            loginInfo.setUserTel("010" + loginInfo.getUserId());
        }

        if (joinMapper.checkUserIdForDuplicate(loginInfo) > 0) {
            return "이미 사용 중인 ID 입니다.";
        }

        joinMapper.insertUserInfo(loginInfo);
        return "정상적으로 회원가입 되었습니다.";
    }

}
