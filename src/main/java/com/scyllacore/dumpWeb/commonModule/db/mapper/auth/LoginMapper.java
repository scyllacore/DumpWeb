package com.scyllacore.dumpWeb.commonModule.db.mapper.auth;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
     AuthDTO selectUserInfoForIdValidCheck(AuthDTO loginInfo);
     AuthDTO selectUserInfoForPwdValidCheck(AuthDTO loginInfo);

     DriverDTO selectDriverInfo(AuthDTO loginInfo);
     SubmitterDTO selectSubmitterInfo(AuthDTO loginInfo);
}
