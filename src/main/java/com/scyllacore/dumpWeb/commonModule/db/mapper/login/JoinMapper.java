package com.scyllacore.dumpWeb.commonModule.db.mapper.login;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinMapper {

     int insertUserInfo(Login login);

     int selectUserIdForDuplicateCheck(Login login);

}
