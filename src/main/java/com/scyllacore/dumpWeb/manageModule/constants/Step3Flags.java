package com.scyllacore.dumpWeb.manageModule.constants;

import lombok.Getter;

@Getter
public enum Step3Flags {
    NEW_DRIVE_REPORT(0),
    ONLY_CHANGE_BY_DRIVER(0),
    ;

    int value;

    Step3Flags(int value) {
        this.value = value;
    }
}