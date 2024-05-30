package com.scyllacore.dumpWeb.manageModule.constants;

import lombok.Getter;

@Getter
public enum Step5Flags {
    NEW_MILEAGE(0);

    int value;

    Step5Flags(int value) {
        this.value = value;
    }
}
