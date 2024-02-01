package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.PageCriteriaDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.PageDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step6ForVehicleManageMileageViewerMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import com.scyllacore.dumpWeb.commonModule.vo.pagination.PageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Step6ForVehicleManageMileageViewerService {
    private final Step6ForVehicleManageMileageViewerMapper step6Mapper;
    private final CommonUtil commonUtil;

    private List<MileageDTO> mileageList;

    public int getUserIdFk() {
        return commonUtil.getLoginInfoBySession().getUserIdIdx();
    }

    public ResponseDTO<String> findMileageListByOption(MileageSearchOptionDTO option) {
        option.setWriterIdFk(getUserIdFk());
        this.mileageList = step6Mapper.selectMileageListByOption(option);
        return new ResponseDTO<>(200, "리스트가 조회 되었습니다");
    }

    public ResponseDTO<PageDTO> findPageList(PageCriteriaDTO pageCriteria) {
        PageVO pageInfo = new PageVO(pageCriteria, mileageList.size());

        return new ResponseDTO<>(200, new PageDTO(pageInfo, mileageList.subList(pageInfo.getStartPage(), pageInfo.getEndPage())));
    }

    public ResponseDTO<String> modifyPaymentInBulk(MileageSearchOptionDTO option) {
        option.setWriterIdFk(getUserIdFk());
        step6Mapper.updateMileagePaymentChk(option);

        if (option.isPaymentBtnFlag()) {
            return new ResponseDTO<>(200, "일괄 결재 되었습니다");
        }

        return new ResponseDTO<>(200, "일괄 취소 되었습니다");
    }
}
