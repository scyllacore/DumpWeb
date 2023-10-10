package com.scyllacore.dumpWeb.commonModule.db.mapper.login;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrialMapper {
    LoginDTO selectTrialUserInfo(LoginDTO loginInfo);
}
