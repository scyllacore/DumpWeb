package com.scyllacore.dumpWeb.commonModule.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Response {
    private HttpStatus httpStatus;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<ValidationError> errors;
    public static Response of(HttpStatus httpStatus, String message){
        return Response.builder()
                .httpStatus(httpStatus)
                .message(message)
                .build();
    }
    public static Response of(HttpStatus httpStatus, String message, BindingResult bindingResult){
        return Response.builder()
                .httpStatus(httpStatus)
                .message(message)
                .errors(ValidationError.of(bindingResult))
                .build();
    }

    @Getter
    public static class ValidationError{
        private final String field;
        private final String value;
        private final String message;

        private ValidationError(FieldError fieldError){
            this.field = fieldError.getField();
            this.value = fieldError.getRejectedValue() == null? "" :fieldError.getRejectedValue().toString() ;
            this.message = fieldError.getDefaultMessage();
        }

        public static List<ValidationError> of(final BindingResult bindingResult){
            return bindingResult.getFieldErrors().stream()
                    .map(ValidationError :: new)
                    .toList();
        }
    }

}
