package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step8ForDriveOrderViewerMapper;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Step8ForDriveOrderViewerService {

    private final SessionUtil sessionUtil;
    private final Step8ForDriveOrderViewerMapper step8Mapper;

    public ResponseEntity<DriveReportSearchOptionDTO.Response> findRecommendKeywordList() {

        DriveReportSearchOptionDTO.Response option = new DriveReportSearchOptionDTO.Response();

        Long submitterId = sessionUtil.getSubmitterInfo().getSubmitterId();

        option.setFromSites(step8Mapper.selectFromSiteSearchOption(submitterId));
        option.setToSites(step8Mapper.selectToSiteSearchOption(submitterId));
        option.setItems(step8Mapper.selectItemSearchOption(submitterId));

        return ResponseEntity.ok(option);
    }

    public ResponseEntity<List<DriveReportDTO.Response>> findDriveOrderListByOption(DriveReportSearchOptionDTO.Request option) {
        option.setSubmitterIdFk(sessionUtil.getSubmitterInfo().getSubmitterId());
        return ResponseEntity.ok(step8Mapper.selectDriveReportListByOption(option));
    }

    @Transactional
    public ResponseEntity<ResponseDTO<String>> modifyPaymentInBulk(DriveReportSearchOptionDTO.Request option) {
        option.setSubmitterIdFk(sessionUtil.getSubmitterInfo().getSubmitterId());
        step8Mapper.updateDriveReportPaymentChk(option);

        if (option.getPaymentBtnFlag()) {
            return ResponseEntity.ok(new ResponseDTO<>("일괄 결재 되었습니다"));
        }

        return ResponseEntity.ok(new ResponseDTO<>("일괄 취소 되었습니다"));
    }

}
