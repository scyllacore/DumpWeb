package com.scyllacore.dumpWeb.commonModule.db.mapper.auth;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import org.apache.ibatis.annotations.Mapper;

public interface PasswordChangeMapper {
    int updateUserPassword(AuthDTO.Password password);
}
