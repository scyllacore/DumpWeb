package com.scyllacore.dumpWeb.dailyReportModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.Summary;
import com.scyllacore.dumpWeb.commonModule.db.dao.DailyReportStep2Main;
import com.scyllacore.dumpWeb.commonModule.db.dao.DailyReportStep2Sub;
import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
import com.scyllacore.dumpWeb.commonModule.db.mapper.DailyReportStep2Mapper;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Step2ServiceForSubmissionMenu {
    private final DailyReportStep2Mapper dailyReportStep2Mapper;
    private final CommonUtil commonUtil;

    public List<DailyReportStep2Sub> getSummary() {
        //1. login 정보 받아오기.
        HttpSession session = commonUtil.getSession();
        Login loginData = (Login) session.getAttribute("loginInfo");

        //2. login id와 tsheet의 CarSubmitTel이 똑같은 튜플 값을 tSheet에서 가져옴.
        List<DailyReportStep2Main> tSheet = new ArrayList<>();
        tSheet = dailyReportStep2Mapper.findDailyReportMainByCarSubmitTel(loginData.getUserId());

        //3. 가져온 튜플의 sheetID와 tSheetSub의 sheetID2가 똑같은 튜플 값을 tSheetSub에서 가져옴
        List<DailyReportStep2Sub> tSheetSub = new ArrayList<>();
        tSheet.stream().forEach(t -> tSheetSub.add(dailyReportStep2Mapper.findDailyReportMainBySheetID2(t.getSheetID())));

        //4. tSheetSub에서 Qty와 QtyUp을 연산하고 누적값을 summaryDAO의 총 운반 금액(setTotalTransportationCost)에 저장.
        Summary summary = new Summary(0, 0, 0, new Date());
        tSheetSub.stream().forEach(t -> summary.setTotalTransportationCost(summary.getTotalTransportationCost() + t.getQty() * t.getQtyup()));

        System.out.println(summary.getTotalTransportationCost());

        return tSheetSub;
    }


}
