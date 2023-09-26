package com.scyllacore.dumpweb.commonModule.db.dto;

import lombok.Data;

@Data
public class Login {

    // tsitepw
    private String uuserID;         // LINE :: 사용자 고유번호
    private String userId;          // LINE :: 사용자 ID
    private String userPw;          // LINE :: 사용자 패스워드
    private String userTel;         // LINE :: 사용자 휴대폰번호
    private String userName;        // LINE :: 사용자명
    private String userPosition;    // LINE :: 사용자 구분(driver: 덤프기사, manager: 제출저 담당자)
    private String userSS;          // LINE :: 회사명
    
    // VO
    private String re_userPw;       // LINE :: 패스워드 확인
    private String type;            // LINE :: 사용자 구분 선택값(덤프기사, 제출저 담당자)
    private boolean testUserChk;    // LINE ::  체험사용자 여부

}
