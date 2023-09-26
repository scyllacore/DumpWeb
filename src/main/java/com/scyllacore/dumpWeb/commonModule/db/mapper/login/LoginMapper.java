package com.scyllacore.dumpWeb.commonModule.db.mapper.login;

import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;

public interface LoginMapper {
     Login userIdValidChk(Login login);
     Login findUserInfo(Login login);

     Login findAdvUserInfo(Login login);
     int userIdDuplicateChk(Login login);

     int insertUserInfo(Login login);

     int updateUserPassword(Login login);
}
