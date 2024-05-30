package com.scyllacore.dumpWeb.commonModule.db.mapper.auth;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import org.apache.ibatis.annotations.Mapper;

public interface TrialMapper {
    AuthDTO.Request selectTrialUserInfo(AuthDTO.TrialChkRequest trial);
}
