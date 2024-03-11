package com.scyllacore.dumpWeb.loginModule.service;

import com.scyllacore.dumpWeb.commonModule.constant.Flag;
import com.scyllacore.dumpWeb.commonModule.constant.ResponseType;
import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.JoinMapper;
import com.scyllacore.dumpWeb.commonModule.exception.RestApiException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class JoinService {

    private final JoinMapper joinMapper;

    @Getter
    public enum Insert {
        DRIVER("driver", Method.DRIVER),
        SUBMITTER("submitter", Method.SUBMITTER);

        private final String key;
        private final Method method;

        Insert(String key, Method method) {
            this.key = key;
            this.method = method;
        }

        private static final Map<String, Method> actionMap =
                Collections.unmodifiableMap(Stream.of(values())
                        .collect(Collectors.toMap(Insert::getKey, Insert::getMethod)));


        public static int find(AuthDTO.Request joinInfo, JoinMapper joinMapper) {
            Method m = Optional.ofNullable(actionMap.get(joinInfo.getUserType())).orElse(Method.DRIVER);
            return m.insert(joinInfo,joinMapper);
        }

        enum Method {
            DRIVER {
                public int insert(AuthDTO.Request joinInfo,JoinMapper joinMapper) {
                    return joinMapper.insertDriverInfo(joinInfo);
                }
            },
            SUBMITTER {
                public int insert(AuthDTO.Request joinInfo,JoinMapper joinMapper) {
                    return joinMapper.insertSubmitterInfo(joinInfo);
                }
            };

            public abstract int insert(AuthDTO.Request joinInfo,JoinMapper joinMapper);
        }
    }

    public ResponseEntity<String> join(AuthDTO.Request joinInfo) throws Exception {
        if (joinMapper.selectUserIdForDuplicateCheck(joinInfo) > 0) {
            throw new DuplicateMemberException("중복 회원");
        }

        if (joinMapper.insertUserInfo(joinInfo) == Flag.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }
        ;

        if (insertUserTypeInfo(joinInfo) == Flag.FAIL.getValue()) {
            throw new RestApiException(ResponseType.SERVICE_UNAVAILABLE);
        }

        return ResponseType.SUCCESS.toResponseEntity();
    }

    private int insertUserTypeInfo(AuthDTO.Request joinInfo) {
        return Insert.find(joinInfo, joinMapper);
    }
}
