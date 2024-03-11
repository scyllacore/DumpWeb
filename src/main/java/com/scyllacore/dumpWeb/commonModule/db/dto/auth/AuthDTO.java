package com.scyllacore.dumpWeb.commonModule.db.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

public class AuthDTO {
    @Data
    static class Request {
        private Long userIdIdx;

        @NotBlank(message = "Id는 비어있으면 안됩니다.")
        private String userId;
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}$"
                , message = "비밀번호는 8~20자 사이의 영문,숫자 조합이어야 합니다.")
        private String userPwd;
        @NotBlank
        private String userType;
        private Boolean trialChk;
        @NotBlank
        private String company;
        @NotBlank
        private String name;
        @Pattern(regexp = "^[0-9]$", message = "전화번호는 숫자로만 이루어져 있어야 합니다.")
        private String tel;
        @NotBlank
        private String carNo;
    }

    @Data
    static class Response {
        private String userId;
        private String userType;
        private LocalDate joinDate;
    }

}
