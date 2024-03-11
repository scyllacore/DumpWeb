package com.scyllacore.dumpWeb.commonModule.db.mapper.auth;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinMapper {

    int insertUserInfo(AuthDTO.Request joinInfo);

    int selectUserIdForDuplicateCheck(AuthDTO.Request joinInfo);

    int insertDriverInfo(AuthDTO.Request driverInfo);

    int insertSubmitterInfo(AuthDTO.Request submitterInfo);

}
