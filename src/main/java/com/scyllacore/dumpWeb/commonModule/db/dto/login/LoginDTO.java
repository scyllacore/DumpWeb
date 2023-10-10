package com.scyllacore.dumpWeb.commonModule.db.dto.login;

import lombok.Data;

@Data
public class LoginDTO {

    private String userIdIdx;         // LINE :: 사용자 고유번호
    private String userId;          // LINE :: 사용자 ID
    private String userPwd;          // LINE :: 사용자 패스워드
    private String userName;        // LINE :: 사용자명
    private String userType;    // LINE :: 사용자 구분(driver: 덤프기사, manager: 제출저 담당자)
    private String userTel;         // LINE :: 사용자 휴대폰번호
    private String joinDate;          // LINE :: 회사명
    private boolean trialChk; // LINE :: 테스트 모드 확인

}
