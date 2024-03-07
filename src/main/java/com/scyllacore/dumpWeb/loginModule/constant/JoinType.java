package com.scyllacore.dumpWeb.loginModule.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public enum JoinType {
    DUPLICATED_ID("이미 사용 중인 ID 입니다", HttpStatus.BAD_REQUEST),
    SUCCESS_JOIN("정상적으로 회원가입 되었습니다.", HttpStatus.OK);

    private String message;
    private HttpStatus httpStatus;

    JoinType(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public ResponseEntity<String> toResponseEntity() {
        return ResponseEntity.status(httpStatus).body(message);
    }
}
