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
    public ResponseEntity<String> join(AuthDTO.Request joinInfo) throws Exception {
        if (joinMapper.selectUserIdForDuplicateCheck(joinInfo) > 0) {
            throw new DuplicateMemberException("중복 회원");
        }

        joinMapper.insertUserInfo(joinInfo);
        insertUserTypeInfo(joinInfo);

        return ResponseType.SUCCESS.toResponseEntity();
    }

    private void insertUserTypeInfo(AuthDTO.Request joinInfo) {
        Map<String, Consumer<AuthDTO.Request>> actionMap = Map.of(
                "driver", join -> this.joinMapper.insertDriverInfo(join),
                "submitter", join -> this.joinMapper.insertSubmitterInfo(join)
        );

        Optional<Consumer<AuthDTO.Request>> action = Optional.of(actionMap.get(joinInfo.getUserType()));
        if (action.isEmpty()) {
            throw new IllegalArgumentException(joinInfo.getUserType());
        }

        action.get().accept(joinInfo);
    }
}
