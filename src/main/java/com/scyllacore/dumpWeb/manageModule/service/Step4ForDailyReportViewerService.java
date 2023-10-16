package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step3ForDriveReportRegistrationMapper;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step4ForDailyReportViewerMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Step4ForDailyReportViewerService {

    private final CommonUtil commonUtil;
    private final Step4ForDailyReportViewerMapper step4Mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public int getUserIdFK() {
        return Integer.parseInt(commonUtil.getLoginInfoBySession().getUserIdIdx());
    }

    public ResponseDTO<DriveReportSearchOptionDTO> findRecommendKeywordList() {

        DriveReportSearchOptionDTO option = new DriveReportSearchOptionDTO();

        option.setCompanyNames(step4Mapper.selectCompanyNameSearchOption(getUserIdFK()));
        option.setFromSites(step4Mapper.selectFromSiteSearchOption(getUserIdFK()));
        option.setToSites(step4Mapper.selectToSiteSearchOption(getUserIdFK()));
        option.setItems(step4Mapper.selectItemSearchOption(getUserIdFK()));
        option.setSubmitterTels(step4Mapper.selectSubmitterTelSearchOption(getUserIdFK()));

        return new ResponseDTO<>(200, option);
    }

    public ResponseDTO<List<DriveReportSearchOptionDTO>> findDriveReportListByOption(DriveReportSearchOptionDTO option) {
        option.setWriterIdFk(getUserIdFK());
        System.out.println(step4Mapper.selectDriveReportListByOption(option));
        return new ResponseDTO<>(200, step4Mapper.selectDriveReportListByOption(option));
    }

    public ResponseDTO<String> modifyPaymentInBulk(DriveReportSearchOptionDTO option) {
        option.setWriterIdFk(getUserIdFK());
        step4Mapper.updateDriveReportPaymentChk(option);

        if (option.isPaymentBtnFlag()) {
            return new ResponseDTO<>(200, "일괄 결재 되었습니다");
        }

        return new ResponseDTO<>(200, "일괄 취소 되었습니다");
    }

}
