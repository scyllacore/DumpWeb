package com.scyllacore.dumpWeb.manageModule.constants;

import lombok.Getter;

@Getter
public enum Step4Flags {
    PAYMENT_DONE("일괄 결재 되었습니다"),
    PAYMENT_CANCEL("일괄 취소 되었습니다");

    private String message;

    Step4Flags(String message) {
        this.message = message;
    }
}