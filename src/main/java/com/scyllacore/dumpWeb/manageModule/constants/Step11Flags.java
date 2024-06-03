package com.scyllacore.dumpWeb.manageModule.constants;

import lombok.Getter;

@Getter
public enum Step11Flags {
    NEW_GROUP_DRIVE_REPORT(0),
    ONLY_CHANGE_BY_SUBMITTER(0),
    NEW_DRIVE_REPORT(0);

    int value;

    Step11Flags(int value) {
        this.value = value;
    }
}