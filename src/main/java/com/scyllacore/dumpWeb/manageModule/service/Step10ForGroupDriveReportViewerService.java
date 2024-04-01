package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step10ForGroupDriveReportViewerMapper;
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
public class Step10ForGroupDriveReportViewerService {

    private final SessionUtil sessionUtil;
    private final Step10ForGroupDriveReportViewerMapper step10Mapper;

    public ResponseEntity<GroupDriveReportSearchOptionDTO.Response> findRecommendKeywordList() {

        GroupDriveReportSearchOptionDTO.Response option = new GroupDriveReportSearchOptionDTO.Response();

        Long driverId = sessionUtil.getDriverInfo().getDriverId();

        option.setTitles(step10Mapper.selectTitleSearchOption(driverId));
        option.setCompanies(step10Mapper.selectCompanySearchOption(driverId));

        return ResponseEntity.ok(option);
    }

    public ResponseEntity<List<GroupDriveReportSearchOptionDTO.Response>> findGroupDriveReportListByOption(GroupDriveReportSearchOptionDTO.Request option) {
        option.setGroupDriverIdFk(sessionUtil.getDriverInfo().getDriverId());
        return ResponseEntity.ok(step10Mapper.selectGroupDriveReportListByOption(option));
    }

    @Transactional
    public ResponseEntity<ResponseDTO<String>> modifyPaymentInBulk(GroupDriveReportSearchOptionDTO.Request option) {
        option.setGroupWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());
        step10Mapper.updateGroupDriveReportPaymentChk(option);

        if (option.getPaymentBtnFlag()) {
            return ResponseEntity.ok(new ResponseDTO<>("일괄 결재 되었습니다"));
        }

        return ResponseEntity.ok(new ResponseDTO<>("일괄 취소 되었습니다"));
    }

}
