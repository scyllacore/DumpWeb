package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step10ForGroupDriveReportViewerMapper;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Step10ForGroupDriveReportViewerService {

    private final CommonUtil commonUtil;
    private final Step10ForGroupDriveReportViewerMapper step10Mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public int getUserIdFk() {
        return commonUtil.getLoginInfoBySession().getUserIdIdx();
    }

    public DriverDTO getDriverInfo() {
        return (DriverDTO) commonUtil.getInfoBySession("driverInfo");
    }

    public ResponseEntity<GroupDriveReportSearchOptionDTO> findRecommendKeywordList() {

        GroupDriveReportSearchOptionDTO option = new GroupDriveReportSearchOptionDTO();

        option.setTitles(step10Mapper.selectTitleSearchOption(getDriverInfo().getDriverId()));
        option.setCompanies(step10Mapper.selectCompanySearchOption(getDriverInfo().getDriverId()));

        return ResponseEntity.ok(option);
    }

    public ResponseEntity<List<GroupDriveReportSearchOptionDTO>> findGroupDriveReportListByOption(GroupDriveReportSearchOptionDTO option) {
        option.setGroupDriverIdFk(getDriverInfo().getDriverId());
        return ResponseEntity.ok( step10Mapper.selectGroupDriveReportListByOption(option));
    }

    @Transactional
    public ResponseEntity<String> modifyPaymentInBulk(GroupDriveReportSearchOptionDTO option) {
        option.setGroupWriterIdFk(getUserIdFk());
        step10Mapper.updateGroupDriveReportPaymentChk(option);
        //step10Mapper.setDriveReportsPaymentChk(option);

        if (option.isPaymentBtnFlag()) {
            return ResponseEntity.ok("일괄 결재 되었습니다");

        }

        return ResponseEntity.ok("일괄 취소 되었습니다");
    }

}
