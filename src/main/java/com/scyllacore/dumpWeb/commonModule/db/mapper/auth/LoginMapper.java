package com.scyllacore.dumpWeb.commonModule.db.mapper.auth;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.UserDetailDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    AuthDTO.Request selectUserInfoForIdValidCheck(AuthDTO.Request loginInfo);

    AuthDTO.Request selectUserInfoForPwdValidCheck(AuthDTO.Request loginInfo);

    UserDetailDTO.Driver selectDriverInfo(AuthDTO.Request loginInfo);

    UserDetailDTO.Submitter selectSubmitterInfo(AuthDTO.Request loginInfo);
}
