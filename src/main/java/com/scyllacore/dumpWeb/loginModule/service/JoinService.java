package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.login.JoinMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final JoinMapper joinMapper;

    public ResponseDTO<String> join(LoginDTO joinInfo) {

        if (joinInfo.getUserType().equals("manager")) {
            joinInfo.setUserTel("010" + joinInfo.getUserId());
        }

        if (joinMapper.selectUserIdForDuplicateCheck(joinInfo) > 0) {
            return new ResponseDTO<String>(403,"이미 사용 중인 ID 입니다.");
        }

        joinMapper.insertUserInfo(joinInfo);
        return new ResponseDTO<String>(200,"정상적으로 회원가입 되었습니다.");
    }

}
