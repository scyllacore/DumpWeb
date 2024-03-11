package com.scyllacore.dumpWeb.commonModule.db.mapper.auth;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinMapper {

    void insertUserInfo(AuthDTO.Request joinInfo);

    int selectUserIdForDuplicateCheck(AuthDTO.Request joinInfo);

    void insertDriverInfo(AuthDTO.Request driverInfo);

    void insertSubmitterInfo(AuthDTO.Request submitterInfo);

}
