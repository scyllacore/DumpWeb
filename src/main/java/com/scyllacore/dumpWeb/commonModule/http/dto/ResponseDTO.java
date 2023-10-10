package com.scyllacore.dumpWeb.commonModule.http.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class ResponseDTO<T> {
    private int status;
    private T data;
}
