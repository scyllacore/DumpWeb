package com.scyllacore.dumpWeb.manageModule.constants;

import lombok.Getter;

@Getter
public enum Step9Flags {
    NEW_GROUP_DRIVE_REPORT(0),
    ONLY_CHANGE_BY_DRIVER(0),
    NEW_DRIVE_REPORT(0),
    NEW_FILE(0);

    int value;

    Step9Flags(int value) {
        this.value = value;
    }
}