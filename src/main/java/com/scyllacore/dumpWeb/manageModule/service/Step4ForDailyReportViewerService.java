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

    public int getUserIdFk() {
        return commonUtil.getLoginInfoBySession().getUserIdIdx();
    }

    public ResponseDTO<DriveReportSearchOptionDTO> findRecommendKeywordList() {

        DriveReportSearchOptionDTO option = new DriveReportSearchOptionDTO();

        option.setCompanies(step4Mapper.selectCompanySearchOption(getUserIdFk()));
        option.setFromSites(step4Mapper.selectFromSiteSearchOption(getUserIdFk()));
        option.setToSites(step4Mapper.selectToSiteSearchOption(getUserIdFk()));
        option.setItems(step4Mapper.selectItemSearchOption(getUserIdFk()));
        option.setTels(step4Mapper.selectSubmitterTelSearchOption(getUserIdFk()));

        return new ResponseDTO<>(200, option);
    }

    public ResponseDTO<List<DriveReportSearchOptionDTO>> findDriveReportListByOption(DriveReportSearchOptionDTO option) {
        option.setWriterIdFk(getUserIdFk());
        System.out.println(step4Mapper.selectDriveReportListByOption(option));
        return new ResponseDTO<>(200, step4Mapper.selectDriveReportListByOption(option));
    }

    public ResponseDTO<String> modifyPaymentInBulk(DriveReportSearchOptionDTO option) {
        option.setWriterIdFk(getUserIdFk());
        step4Mapper.updateDriveReportPaymentChk(option);

        if (option.isPaymentBtnFlag()) {
            return new ResponseDTO<>(200, "일괄 결재 되었습니다");
        }

        return new ResponseDTO<>(200, "일괄 취소 되었습니다");
    }

}
