package com.scyllacore.dumpWeb.commonModule.db.mapper.login;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinMapper {

     int insertUserInfo(LoginDTO loginInfo);

     int selectUserIdForDuplicateCheck(LoginDTO loginInfo);

}
