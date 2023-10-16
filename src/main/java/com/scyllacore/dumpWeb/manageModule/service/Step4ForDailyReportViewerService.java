package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step3ForDriveReportRegistrationMapper;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step4ForDailyReportViewerMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Step4ForDailyReportViewerService {

    private final CommonUtil commonUtil;
    private final Step4ForDailyReportViewerMapper step4Mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public int getUserIdFK() {
        return Integer.parseInt(commonUtil.getLoginInfoBySession().getUserIdIdx());
    }

    public ResponseDTO<DriveReportSearchOptionDTO> findRecommendKeyword() {

        DriveReportSearchOptionDTO option = new DriveReportSearchOptionDTO();

        option.setCompanyNames(step4Mapper.selectCompanyNameSearchOption(getUserIdFK()));
        option.setFromSites(step4Mapper.selectFromSiteSearchOption(getUserIdFK()));
        option.setToSites(step4Mapper.selectToSiteSearchOption(getUserIdFK()));
        option.setItems(step4Mapper.selectItemSearchOption(getUserIdFK()));
        option.setSubmitterTels(step4Mapper.selectSubmitterTelSearchOption(getUserIdFK()));

        System.out.println(option);

        return new ResponseDTO<>(200, option);
    }

}
