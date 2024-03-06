package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step8ForSubmitterReceiptViewerMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Step8ForSubmitterReceiptViewerService {

    private final CommonUtil commonUtil;
    private final Step8ForSubmitterReceiptViewerMapper step8Mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public SubmitterDTO getSubmitterInfo() {
        return (SubmitterDTO) commonUtil.getInfoBySession("submitterInfo");
    }

    public ResponseDTO<DriveReportSearchOptionDTO> findRecommendKeywordList() {

        DriveReportSearchOptionDTO option = new DriveReportSearchOptionDTO();

        option.setCarNos(step8Mapper.selectCarNoSearchOption(getSubmitterInfo().getSubmitterId()));
        option.setFromSites(step8Mapper.selectFromSiteSearchOption(getSubmitterInfo().getSubmitterId()));
        option.setToSites(step8Mapper.selectToSiteSearchOption(getSubmitterInfo().getSubmitterId()));
        option.setItems(step8Mapper.selectItemSearchOption(getSubmitterInfo().getSubmitterId()));

        return new ResponseDTO<>(200, option);
    }

    public ResponseDTO<List<DriveReportSearchOptionDTO>> findDriveReportListByOption(DriveReportSearchOptionDTO option) {
        option.setSubmitterIdFk(getSubmitterInfo().getSubmitterId());
        return new ResponseDTO<>(200, step8Mapper.selectDriveReportListByOption(option));
    }

    @Transactional
    public ResponseDTO<String> modifyPaymentInBulk(DriveReportSearchOptionDTO option) {
        option.setSubmitterIdFk(getSubmitterInfo().getSubmitterId());
        step8Mapper.updateDriveReportPaymentChk(option);

        if (option.isPaymentBtnFlag()) {
            return new ResponseDTO<>(200, "일괄 결재 되었습니다");
        }

        return new ResponseDTO<>(200, "일괄 취소 되었습니다");
    }

}
