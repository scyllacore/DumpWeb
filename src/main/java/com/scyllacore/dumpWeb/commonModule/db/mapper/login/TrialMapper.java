package com.scyllacore.dumpWeb.commonModule.db.mapper.login;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrialMapper {
    Login selectTrialUserInfo(Login login);
}
