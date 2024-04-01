package com.scyllacore.dumpWeb.commonModule.constant;

import lombok.Getter;

@Getter
public enum OperationStatus {

    SUCCESS(1),
    FAIL(0);
    private int value;

    OperationStatus(int value) {
        this.value = value;
    }

}
