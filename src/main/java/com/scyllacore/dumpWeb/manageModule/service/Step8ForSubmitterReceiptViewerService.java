package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SubmitterDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step8ForSubmitterReceiptViewerMapper;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<DriveReportSearchOptionDTO> findRecommendKeywordList() {

        DriveReportSearchOptionDTO option = new DriveReportSearchOptionDTO();

        option.setCarNos(step8Mapper.selectCarNoSearchOption(getSubmitterInfo().getSubmitterId()));
        option.setFromSites(step8Mapper.selectFromSiteSearchOption(getSubmitterInfo().getSubmitterId()));
        option.setToSites(step8Mapper.selectToSiteSearchOption(getSubmitterInfo().getSubmitterId()));
        option.setItems(step8Mapper.selectItemSearchOption(getSubmitterInfo().getSubmitterId()));

        return new ResponseEntity<>(200, option);
    }

    public ResponseEntity<List<DriveReportSearchOptionDTO>> findDriveReportListByOption(DriveReportSearchOptionDTO option) {
        option.setSubmitterIdFk(getSubmitterInfo().getSubmitterId());
        return new ResponseEntity<>(200, step8Mapper.selectDriveReportListByOption(option));
    }

    @Transactional
    public ResponseEntity<String> modifyPaymentInBulk(DriveReportSearchOptionDTO option) {
        option.setSubmitterIdFk(getSubmitterInfo().getSubmitterId());
        step8Mapper.updateDriveReportPaymentChk(option);

        if (option.isPaymentBtnFlag()) {
            return new ResponseEntity<>(200, "일괄 결재 되었습니다");
        }

        return new ResponseEntity<>(200, "일괄 취소 되었습니다");
    }

}
