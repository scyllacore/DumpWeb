package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.constant.OperationStatus;
import com.scyllacore.dumpWeb.commonModule.constant.ResponseType;
import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.JoinMapper;
import com.scyllacore.dumpWeb.commonModule.exception.RestApiException;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.loginModule.method.Insert;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class JoinService {

    private final JoinMapper joinMapper;

    public ResponseEntity<ResponseDTO<String>> join(AuthDTO.Request joinInfo) throws Exception {
        if (joinMapper.selectUserIdForDuplicateCheck(joinInfo) > 0) {
            throw new DuplicateMemberException("중복 회원");
        }

        if (joinMapper.insertUserInfo(joinInfo) == OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
        ;

        if (insertUserTypeInfo(joinInfo) == OperationStatus.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }

        return ResponseType.SUCCESS.toResponseEntity();
    }

    private int insertUserTypeInfo(AuthDTO.Request joinInfo) {
        return Insert.find(joinInfo, joinMapper);
    }
}
