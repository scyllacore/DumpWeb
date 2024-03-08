package com.scyllacore.dumpWeb.commonModule.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@RequiredArgsConstructor
public enum ResponseType {
    SUCCESS(HttpStatus.OK, "정상적으로 처리 되었습니다."),

    DUPLICATED_ID(HttpStatus.BAD_REQUEST, "이미 사용 중인 ID 입니다"),
    NOT_SUPPORT_USER_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 유저 타입입니다."),
    INVALID_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, "올바르지 않은 파라미터입니다."),
    INVALID_FORMAT_ERROR(HttpStatus.BAD_REQUEST, "올바르지 않은 포맷입니다."),
    INVALID_TYPE_ERROR(HttpStatus.BAD_REQUEST, "올바르지 않은 타입입니다."),
    ILLEGAL_ARGUMENT_ERROR(HttpStatus.BAD_REQUEST, "필수 파라미터가 없습니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),

    MEMBER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND,"존재하지 않는 사용자입니다"),
    MEMBER_ALREADY_EXISTS_ERROR(HttpStatus.CONFLICT, "는 이미 존재하는 회원입니다"),
    INACTIVE_USER_ERROR(HttpStatus.FORBIDDEN,"권한이 없는 사용자입니다");

    private final HttpStatus httpStatus;
    private final String message;

    public ResponseEntity<String> toResponseEntity() {
        return ResponseEntity.status(httpStatus).body(message);
    }

}
