package com.scyllacore.dumpWeb.commonModule.db.mapper.auth;

import com.scyllacore.dumpWeb.commonModule.db.dto.auth.AuthDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinMapper {

    void insertUserInfo(AuthDTO joinInfo);

    int selectUserIdForDuplicateCheck(AuthDTO joinInfo);

    void insertDriverInfo(AuthDTO driverInfo);

    void insertSubmitterInfo(AuthDTO submitterInfo);

}
