package com.scyllacore.dumpWeb.commonModule.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDTO<T> {
    private T data;
}
