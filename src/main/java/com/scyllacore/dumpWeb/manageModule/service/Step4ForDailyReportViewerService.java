package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step4ForDailyReportViewerMapper;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.SessionUtil;
import com.scyllacore.dumpWeb.manageModule.constants.Step4Flags;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Step4ForDailyReportViewerService {

    private final SessionUtil sessionUtil;
    private final Step4ForDailyReportViewerMapper step4Mapper;

    public ResponseEntity<DriveReportSearchOptionDTO.Response> findRecommendKeywordList() {

        DriveReportSearchOptionDTO.Response response = new DriveReportSearchOptionDTO.Response();
        Long driverId = sessionUtil.getDriverInfo().getDriverId();

        response.setFromSites(step4Mapper.selectFromSiteSearchOption(driverId));
        response.setToSites(step4Mapper.selectToSiteSearchOption(driverId));
        response.setItems(step4Mapper.selectItemSearchOption(driverId));

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<List<DriveReportDTO.Response>> findDriveReportListByOption(
            DriveReportSearchOptionDTO.Request option) {
        option.setDriverIdFk(sessionUtil.getDriverInfo().getDriverId());
        return ResponseEntity.ok(step4Mapper.selectDriveReportListByOption(option));
    }

    @Transactional
    public ResponseEntity<ResponseDTO<String>> modifyPaymentInBulk(DriveReportSearchOptionDTO.Request option) {
        option.setWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());
        step4Mapper.updateDriveReportPaymentChk(option);

        if (option.getPaymentBtnFlag()) {
            return ResponseEntity.ok(new ResponseDTO<>(Step4Flags.PAYMENT_DONE.getMessage()));
        }

        return ResponseEntity.ok(new ResponseDTO<>(Step4Flags.PAYMENT_CANCEL.getMessage()));
    }

}
