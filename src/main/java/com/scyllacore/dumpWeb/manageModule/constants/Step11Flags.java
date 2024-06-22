package com.scyllacore.dumpWeb.manageModule.constants;

import lombok.Getter;

@Getter
public enum Step11Flags {
    NEW_GROUP_ORDER_REPORT(0),
    ONLY_CHANGE_BY_SUBMITTER(1),
    NEW_ORDER_REPORT(0),
    NEW_FILE(0),
    DRIVER_SET(0);

    int value;

    Step11Flags(int value) {
        this.value = value;
    }
}