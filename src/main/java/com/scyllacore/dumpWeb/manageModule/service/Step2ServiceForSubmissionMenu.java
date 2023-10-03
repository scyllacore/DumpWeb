package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SearchOption;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.Summary;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.TSheetSub;
import com.scyllacore.dumpWeb.commonModule.db.dto.login.Login;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step2MapperForSubmissionMenu;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Step2ServiceForSubmissionMenu {
    private final Step2MapperForSubmissionMenu step2Mapper;
    private final CommonUtil commonUtil;

    public String getToday() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }

    public Summary summarize(SearchOption option) {
        return step2Mapper.selectComputedSummary(option, commonUtil.getLoginInfoBySession().getUserId());
    }

    public List<TSheetSub> findTodayDispatchStatus() {
        return step2Mapper.selectTodayDispatchStatusList(commonUtil.getLoginInfoBySession().getUserId(), getToday());
    }

}
