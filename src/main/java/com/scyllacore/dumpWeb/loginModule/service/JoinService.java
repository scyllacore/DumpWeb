package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.constant.ResponseType;
import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.JoinMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Transactional
public class JoinService {

    private final JoinMapper joinMapper;

    private final Map<String, Consumer<AuthDTO>> actionMap = Map.of(
            "driver", joinInfo -> joinMapper.insertDriverInfo(joinInfo),
            "submitter", joinInfo -> joinMapper.insertSubmitterInfo(joinInfo)
    );

    public ResponseEntity<String> join(AuthDTO joinInfo) throws Exception {
        if (joinMapper.selectUserIdForDuplicateCheck(joinInfo) > 0) {
            throw new DuplicateMemberException("중복 회원");
        }

        joinMapper.insertUserInfo(joinInfo);
        insertUserTypeInfo(joinInfo);

        return ResponseType.SUCCESS.toResponseEntity();
    }

    private void insertUserTypeInfo(AuthDTO joinInfo) {
        Optional<Consumer<AuthDTO>> action = Optional.of(actionMap.get(joinInfo.getUserType()));
        if (action.isEmpty()) {
            throw new IllegalArgumentException(joinInfo.getUserType());
        }

        action.get().accept(joinInfo);
    }
}
