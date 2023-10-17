package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.JoinMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final JoinMapper joinMapper;

    public ResponseDTO<String> join(AuthDTO joinInfo) {
        
        if (joinMapper.selectUserIdForDuplicateCheck(joinInfo) > 0) {
            return new ResponseDTO<String>(403,"이미 사용 중인 ID 입니다.");
        }
        
        joinMapper.insertUserInfo(joinInfo);

        if (joinInfo.getUserType().equals("driver")) {
            joinMapper.insertDriverInfo(joinInfo);
        }
        else{
            joinMapper.insertSubmitterInfo(joinInfo);
        }
        
        return new ResponseDTO<String>(200,"정상적으로 회원가입 되었습니다.");
    }

}
