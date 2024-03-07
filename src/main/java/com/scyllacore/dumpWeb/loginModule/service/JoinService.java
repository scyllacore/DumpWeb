package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.JoinMapper;
import com.scyllacore.dumpWeb.loginModule.constant.JoinType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
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

    public ResponseEntity<String> join(AuthDTO joinInfo) {
        if (joinMapper.selectUserIdForDuplicateCheck(joinInfo) > 0) {
            return JoinType.DUPLICATED_ID.toResponseEntity();
        }

        joinMapper.insertUserInfo(joinInfo);
        insertUserTypeInfo(joinInfo);

        return JoinType.SUCCESS_JOIN.toResponseEntity();
    }

    private void insertUserTypeInfo(AuthDTO joinInfo) {
        Consumer<AuthDTO> action = actionMap.get(joinInfo.getUserType());
        if (action == null) {
            throw new IllegalArgumentException(JoinType.NOT_SUPPORT_USER_TYPE.getMessage() + joinInfo.getUserType());
        }

        action.accept(joinInfo);
    }
}
