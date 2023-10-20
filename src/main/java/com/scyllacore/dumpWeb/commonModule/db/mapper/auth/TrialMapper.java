package com.scyllacore.dumpWeb.commonModule.db.mapper.auth;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrialMapper {
    AuthDTO selectTrialUserInfo(AuthDTO loginInfo);
}
