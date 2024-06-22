package com.scyllacore.dumpWeb.manageModule.service;


import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.GroupDriveReportSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step10ForGroupDriveReportViewerMapper;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step12ForGroupOrderReportViewerMapper;
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
public class Step12ForGroupOrderReportViewerService {

    private final SessionUtil sessionUtil;
    private final Step12ForGroupOrderReportViewerMapper step12Mapper;

    public ResponseEntity<GroupDriveReportSearchOptionDTO.Response> findRecommendKeywordList() {

        GroupDriveReportSearchOptionDTO.Response option = new GroupDriveReportSearchOptionDTO.Response();

        Long submitterId = sessionUtil.getSubmitterInfo().getSubmitterId();

        option.setTitles(step12Mapper.selectTitleSearchOption(submitterId));
        option.setCarNos(step12Mapper.selectCarNoSearchOption(submitterId));

        return ResponseEntity.ok(option);
    }

    public ResponseEntity<List<GroupDriveReportDTO.Response>> findGroupOrderReportListByOption(GroupDriveReportSearchOptionDTO.Request option) {
        option.setGroupSubmitterIdFk(sessionUtil.getSubmitterInfo().getSubmitterId());
        return ResponseEntity.ok(step12Mapper.selectGroupOrderReportListByOption(option));
    }

    @Transactional
    public ResponseEntity<ResponseDTO<String>> modifyPaymentInBulk(GroupDriveReportSearchOptionDTO.Request option) {
        option.setGroupWriterIdFk(sessionUtil.getLoginInfo().getUserIdIdx());
        step12Mapper.updateGroupOrderReportPaymentChk(option);

        if (option.getPaymentBtnFlag()) {
            return ResponseEntity.ok(new ResponseDTO<>("일괄 결재 되었습니다"));
        }

        return ResponseEntity.ok(new ResponseDTO<>("일괄 취소 되었습니다"));
    }

}
