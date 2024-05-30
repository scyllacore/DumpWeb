package com.scyllacore.dumpWeb.loginModule.method;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.auth.JoinMapper;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return m.insert(joinInfo, joinMapper);
    }

    enum Method {
        DRIVER((joinInfo, joinMapper) -> joinMapper.insertDriverInfo(joinInfo)),
        SUBMITTER((joinInfo, joinMapper) -> joinMapper.insertSubmitterInfo(joinInfo));

        private final Method.JoinFunction insertFunction;

        Method(Method.JoinFunction insertFunction) {
            this.insertFunction = insertFunction;
        }

        public int insert(AuthDTO.Request joinInfo, JoinMapper joinMapper) {
            return insertFunction.insert(joinInfo, joinMapper);
        }

        interface JoinFunction {
            int insert(AuthDTO.Request joinInfo, JoinMapper joinMapper);
        }
    }
}