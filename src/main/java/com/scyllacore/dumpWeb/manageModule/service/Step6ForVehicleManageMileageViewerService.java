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

    public int getUserIdFk() {
        return commonUtil.getLoginInfoBySession().getUserIdIdx();
    }

    public ResponseDTO<PageDTO> findMileageListByOption(MileageSearchOptionDTO option) {

        option.setWriterIdFk(getUserIdFk());

        PageDTO pageDTO = new PageDTO();
        pageDTO.setOption(option);

        int totalAmount = step6Mapper.countMileageListByOption(pageDTO);

        PageCriteriaDTO criteria = new PageCriteriaDTO(option.getPageNum(),option.getPageAmount());
        PageVO pageInfo =  new PageVO(criteria,totalAmount);
        pageDTO.setPageInfo(pageInfo);

        System.out.println(pageInfo.getStart() + " " + pageInfo.getEnd());

        List<MileageDTO> mileageList = step6Mapper.selectMileageListByOption(pageDTO);
        System.out.println(mileageList);

        pageDTO.setMileageList(mileageList);

        return new ResponseDTO<>(200, pageDTO);
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
