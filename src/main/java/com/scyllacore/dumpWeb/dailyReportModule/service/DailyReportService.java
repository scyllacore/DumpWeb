package com.scyllacore.dumpweb.dailyReportModule.service;

import com.scyllacore.dumpweb.commonModule.db.dto.DailyReport;
import com.scyllacore.dumpweb.commonModule.db.dto.Login;
import com.scyllacore.dumpweb.commonModule.db.mapper.DailyReportMapper;
import com.scyllacore.dumpweb.commonModule.util.CommonUtil;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.ICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DailyReportService {

    private final DailyReportMapper dailyReportMapper;
    private final CommonUtil commonUtil;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * FUNCTION :: 일보 리스트 페이지
     * @param model
     * @param dailyReport
     * @return
     */
    public void list(Model model, DailyReport dailyReport) {
        HttpSession session = commonUtil.getSession();

        try {
            Login loginData = (Login) session.getAttribute("loginInfo");
            dailyReport.setCarNo(loginData.getUserId());

            log.info(dailyReport.getCarNo());
            List<DailyReport> list = dailyReportMapper.findDailyReportList(dailyReport);
            model.addAttribute("list", list);
        } catch (Exception e) {
            log.error("Exception["+ e.getMessage() +"]");
        }
    }

    public void form(Model model, DailyReport dailyReport) {
        HttpSession session = commonUtil.getSession();
        Login loginData = (Login) session.getAttribute("loginInfo");
        dailyReport.setCarNo(loginData.getUserId());

        DailyReport view = dailyReportMapper.findDailyReportBySheetID(dailyReport);

        if (view != null) {

            List<DailyReport> groupList = dailyReportMapper.findDailyReportListByCarSubmit(dailyReport);
            model.addAttribute("view", view);
            model.addAttribute("groupList", groupList);
        }

    }

    public String save(DailyReport dailyReport) {
        Map<String, Object> rtnMap = commonUtil.returnMap();
        HttpSession session = commonUtil.getSession();

        try {
            Login loginData = (Login) session.getAttribute("loginInfo");
            dailyReport.setWriter(loginData.getUserName());
            if (dailyReport.getSheetID() == 0) {
                dailyReportMapper.insertDailyReport(dailyReport);
            } else  {
                dailyReportMapper.updateDailyReport(dailyReport);
            }
            rtnMap.put("httpCode", 200);
        } catch (Exception e) {
            log.error("Exception["+ e.getMessage() +"]");
        }
        return commonUtil.jsonFormatTransfer(rtnMap);
    }

    public String delete(DailyReport dailyReport) {
        Map<String, Object> rtnMap = commonUtil.returnMap();

        try {
            dailyReportMapper.deleteDailyReport(dailyReport);
            rtnMap.put("httpCode", 200);
        } catch (Exception e) {
            log.error("Exception["+ e.getMessage() +"]");
        }

        return commonUtil.jsonFormatTransfer(rtnMap);
    }

    public String dataSetting(DailyReport dailyReport) {
        Map<String, Object> rtnMap = commonUtil.returnMap();

        try {
            DailyReport settingData = dailyReportMapper.findDailyReportBySheetID(dailyReport);
            rtnMap.put("httpCode", 200);
            rtnMap.put("settingData", settingData);
        } catch (Exception e) {
            log.error("Exception["+ e.getMessage() +"]");
        }

        return commonUtil.jsonFormatTransfer(rtnMap);
    }


}
