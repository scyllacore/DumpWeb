package com.scyllacore.dumpWeb.manageModule.constants;

import lombok.Getter;

@Getter
public enum Step7Flags {
    NEW_ORDER_REPORT(0),
    ONLY_CHANGE_BY_SUBMITTER(1),
    ;

    int value;

    Step7Flags(int value) {
        this.value = value;
    }
}
