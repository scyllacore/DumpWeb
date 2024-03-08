package com.scyllacore.dumpWeb.commonModule.exception;

import com.scyllacore.dumpWeb.commonModule.constant.ResponseType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException{
    private final ResponseType errorType;
}
