package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.JoinMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.loginModule.constant.JoinType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class JoinService {

    private final JoinMapper joinMapper;

    public ResponseEntity<String> join(AuthDTO joinInfo) {

        if (joinMapper.selectUserIdForDuplicateCheck(joinInfo) > 0) {
            return JoinType.DUPLICATED_ID.toResponseEntity();
        }

        joinMapper.insertUserInfo(joinInfo);

        if (joinInfo.getUserType().equals("driver")) {
            joinMapper.insertDriverInfo(joinInfo);
        }
        else{
            joinMapper.insertSubmitterInfo(joinInfo);
        }

        return JoinType.SUCCESS_JOIN.toResponseEntity();
    }

}
