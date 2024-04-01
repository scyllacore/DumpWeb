package com.scyllacore.dumpWeb.commonModule.exception.advice;

import com.scyllacore.dumpWeb.commonModule.constant.ResponseType;
import com.scyllacore.dumpWeb.commonModule.exception.RestApiException;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        return createResponseEntity(ResponseType.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNullPointerException(NullPointerException e){
        return createResponseEntity(ResponseType.INVALID_REQUEST_PARAMETER, e);
    }

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity handleRuntimeException(RestApiException e) {
        return createResponseEntity(e.getErrorType(), e);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e){
        return createResponseEntity(ResponseType.INVALID_REQUEST_PARAMETER, e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return createResponseEntity(ResponseType.INVALID_REQUEST_PARAMETER, e);
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity handleDuplicateMemberException(DuplicateMemberException e){
        return createResponseEntity(ResponseType.DUPLICATED_ID, e);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIOException(IOException e) {
        return createResponseEntity(ResponseType.INTERNAL_SERVER_ERROR, e);
    }

    private ResponseEntity createResponseEntity(ResponseType errorType, Exception e) {
        log.error("[Exception] cause: {} , message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
        ErrorResponse error = ErrorResponse.create(e, errorType.getHttpStatus(),
                String.format("%s\n detail : %s",errorType.getMessage(),NestedExceptionUtils.getMostSpecificCause(e)));
        return ResponseEntity.status(error.getStatusCode()).body(error.getBody());
    }
}
