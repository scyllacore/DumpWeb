package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.DriverDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step10ForGroupDriveReportViewerMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
    public ResponseDTO<GroupDriveReportSearchOptionDTO> findRecommendKeywordList() {

        GroupDriveReportSearchOptionDTO option = new GroupDriveReportSearchOptionDTO();

        option.setTitles(step10Mapper.selectSubmitterTelSearchOption(getDriverInfo().getDriverId()));
        option.setCompanies(step10Mapper.selectCompanySearchOption(getDriverInfo().getDriverId()));

        return new ResponseDTO<>(200, option);
    }

    public ResponseDTO<List<GroupDriveReportSearchOptionDTO>> findDriveReportListByOption(GroupDriveReportSearchOptionDTO option) {
        option.setGroupDriverIdFk(getDriverInfo().getDriverId());
        return new ResponseDTO<>(200, step10Mapper.selectDriveReportListByOption(option));
    }

    public ResponseDTO<String> modifyPaymentInBulk(GroupDriveReportSearchOptionDTO option) {
        option.setGroupWriterIdFk(getUserIdFk());
        step10Mapper.updateDriveReportPaymentChk(option);

        if (option.isPaymentBtnFlag()) {
            return new ResponseDTO<>(200, "일괄 결재 되었습니다");
        }

        return new ResponseDTO<>(200, "일괄 취소 되었습니다");
    }

}
