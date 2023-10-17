package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step6ForVehicleManageMileageViewerMapper;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Step6ForVehicleManageMileageViewerService {
    private final Step6ForVehicleManageMileageViewerMapper step6Mapper;
    private final CommonUtil commonUtil;

    public int getUserIdFK() {
        return commonUtil.getLoginInfoBySession().getUserIdIdx();
    }

    public ResponseDTO<List<MileageDTO>> findMileageListByOption(MileageSearchOptionDTO option) {
        option.setWriterIdFk(getUserIdFK());
        return new ResponseDTO<>(200, step6Mapper.selectMileageListByOption(option));
    }

    public ResponseDTO<String> modifyPaymentInBulk(MileageSearchOptionDTO option) {
        option.setWriterIdFk(getUserIdFK());
        step6Mapper.updateMileagePaymentChk(option);

        if (option.isPaymentBtnFlag()) {
            return new ResponseDTO<>(200, "일괄 결재 되었습니다");
        }

        return new ResponseDTO<>(200, "일괄 취소 되었습니다");
    }
}
