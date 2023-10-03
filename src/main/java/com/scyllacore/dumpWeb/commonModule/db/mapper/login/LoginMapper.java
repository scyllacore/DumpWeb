package com.scyllacore.dumpWeb.commonModule.db.mapper.login;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
     LoginDTO selectUserInfoForValidCheck(LoginDTO loginInfo);
     LoginDTO selectUserInfo(LoginDTO loginInfo);
}
