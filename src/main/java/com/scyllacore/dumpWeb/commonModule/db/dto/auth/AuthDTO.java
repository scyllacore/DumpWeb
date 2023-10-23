package com.scyllacore.dumpWeb.commonModule.db.dto.auth;

import lombok.Data;

@Data
public class AuthDTO {

    private int userIdIdx;
    private String userId;
    private String userPwd;
    private String userType;
    private String joinDate;
    private boolean trialChk;

    private String company;
    private String name;
    private String tel;
    private String carNo;

}
