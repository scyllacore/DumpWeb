package com.scyllacore.dumpWeb.dailyReportModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.DailyReportStep3Main;
import com.scyllacore.dumpWeb.commonModule.db.dto.DailyReportStep3Sub;
import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
import com.scyllacore.dumpWeb.commonModule.db.mapper.DailyReportStep3MainMapper;
import com.scyllacore.dumpWeb.commonModule.db.mapper.DailyReportStep3SubMapper;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class Step3ServiceForDailyReportRegistration {

    private final DailyReportStep3MainMapper dailyReportStep3MainMapper;
    private final DailyReportStep3SubMapper dailyReportStep3SubMapper;
    private final CommonUtil commonUtil;
    private final Logger log = LoggerFactory.getLogger(this.getClass());


    //제출처(부모테이블), 상하차정보(자식테이블) 동시 저장
    public String save(DailyReportStep3Main dailyReportStep3Main, DailyReportStep3Sub dailyReportStep3Sub) {
        Map<String, Object> rtnMap = commonUtil.returnMap();
        HttpSession session = commonUtil.getSession();

        try {
            Login loginData = (Login) session.getAttribute("loginInfo");
            dailyReportStep3Main.setCarNo(loginData.getUserId());
            if (dailyReportStep3Main.getSheetID() == 0) {
                //제출처 정보가 조회된 게 있다면

                //제출처 정보가 조회된 게 없다면
                //dailyReportMain
                dailyReportStep3MainMapper.insertDailyReportMain(dailyReportStep3Main);
                //위에서 등록한 idx를 등록하기(mapper에 selectKey로 등록해둠)
                dailyReportStep3Sub.setSheetID2(dailyReportStep3Main.getSheetID());
                //dailyReportSub
                dailyReportStep3SubMapper.insertDailyReportSub(dailyReportStep3Sub);
            } else  {
                //dailyReportMainMapper.updateDailyReport(dailyReportMain);
            }
            rtnMap.put("httpCode", 200);
        } catch (Exception e) {
            log.error("Exception["+ e.getMessage() +"]");
        }
        return commonUtil.jsonFormatTransfer(rtnMap); }

}
