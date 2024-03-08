package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.PageCriteriaDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.PageDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step6ForVehicleManageMileageViewerMapper;
import org.springframework.http.ResponseEntity;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import com.scyllacore.dumpWeb.commonModule.vo.pagination.PageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Step6ForVehicleManageMileageViewerService {
    private final Step6ForVehicleManageMileageViewerMapper step6Mapper;
    private final CommonUtil commonUtil;

    public int getUserIdFk() {
        return commonUtil.getLoginInfoBySession().getUserIdIdx();
    }

    public ResponseEntity<PageDTO> findMileageListByOption(MileageSearchOptionDTO option) {

        option.setWriterIdFk(getUserIdFk());

        PageDTO pageDTO = new PageDTO();
        pageDTO.setOption(option);

        int totalAmount = step6Mapper.countMileageListByOption(pageDTO);

        PageCriteriaDTO criteria = new PageCriteriaDTO(option.getPageNum(),option.getPageAmount());
        PageVO pageInfo =  new PageVO(criteria,totalAmount);
        pageDTO.setPageInfo(pageInfo);

        List<MileageDTO> mileageList = step6Mapper.selectMileageListByOption(pageDTO);

        pageDTO.setMileageList(mileageList);

        return ResponseEntity.ok(pageDTO);
    }

    @Transactional
    public ResponseEntity<String> modifyPaymentInBulk(MileageSearchOptionDTO option) {
        option.setWriterIdFk(getUserIdFk());
        step6Mapper.updateMileagePaymentChk(option);

        if (option.isPaymentBtnFlag()) {
            return ResponseEntity.ok("일괄 결재 되었습니다");

        }
        return ResponseEntity.ok("일괄 취소 되었습니다");

    }
}
