package com.scyllacore.dumpWeb.commonModule.constant;

import lombok.Getter;

@Getter
public enum Flag {

    SUCCESS(1),
    FAIL(0);
    private int value;

    Flag(int value) {
        this.value = value;
    }

}
