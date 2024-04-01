package com.scyllacore.dumpWeb.commonModule.constant;

import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@RequiredArgsConstructor
public enum ResponseType {
    SUCCESS(HttpStatus.OK, "정상적으로 처리 되었습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "요청하신 파일을 찾을 수 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다. 로그인 후 다시 시도해주세요."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "요청하신 리소스를 찾을 수 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 요청 방식입니다."),
    REQUEST_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "요청 시간이 초과되었습니다. 잠시 후 다시 시도해주세요."),
    CONFLICT(HttpStatus.CONFLICT, "요청에 충돌이 발생했습니다."),
    ENTITY_TOO_LARGE(HttpStatus.PAYLOAD_TOO_LARGE, "요청 데이터가 너무 큽니다."),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "지원하지 않는 미디어 타입입니다."),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "요청 횟수가 제한을 초과했습니다. 잠시 후 다시 시도해주세요."),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "서비스가 일시적으로 사용할 수 없습니다. 잠시 후 다시 시도해주세요."),

    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "잘못된 인증 정보입니다. 다시 확인해주세요."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다. 다시 로그인 후 시도해주세요."),
    INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, "요청 본문이 올바르지 않습니다. 형식을 확인해주세요."),
    INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "요청 파라미터가 올바르지 않습니다. 형식을 확인해주세요."),
    INVALID_REQUEST_HEADER(HttpStatus.BAD_REQUEST, "요청 헤더가 올바르지 않습니다. 형식을 확인해주세요."),
    INVALID_REQUEST_QUERY(HttpStatus.BAD_REQUEST, "요청 쿼리 파라미터가 올바르지 않습니다. 형식을 확인해주세요."),
    INVALID_REQUEST_PATH(HttpStatus.BAD_REQUEST, "요청 경로가 올바르지 않습니다. 형식을 확인해주세요."),
    INVALID_REQUEST_METHOD(HttpStatus.METHOD_NOT_ALLOWED, "요청 방식이 올바르지 않습니다. 형식을 확인해주세요."),

    DUPLICATED_ID(HttpStatus.BAD_REQUEST, "이미 사용 중인 ID 입니다"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,"존재하지 않는 사용자입니다"),
    INACTIVE_USER(HttpStatus.FORBIDDEN,"권한이 없는 사용자입니다");

    private final HttpStatus httpStatus;
    private final String message;

    public ResponseEntity<ResponseDTO<String>> toResponseEntity() {
        return ResponseEntity.status(httpStatus).body(new ResponseDTO<>(message));
    }

}
