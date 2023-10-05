package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step6ForVehicleManageMileageViewerMapper;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Step6ForVehicleManageMileageViewerService {
    private final Step6ForVehicleManageMileageViewerMapper step6Mapper;
    private final CommonUtil commonUtil;


    public List<MileageDTO> findMileageListByOption(SearchOptionDTO option) {
        option.setCarNo(commonUtil.getLoginInfoBySession().getUserId());
        return step6Mapper.selectMileageListByOption(option);
    }

    public void approvePaymentByMileageChk2(SearchOptionDTO option) {
        option.setCarNo(commonUtil.getLoginInfoBySession().getUserId());
        step6Mapper.updateMileagePaymentChkForApprove(option);
    }

    public void cancelPaymentByMileageChk2(SearchOptionDTO option) {
        option.setCarNo(commonUtil.getLoginInfoBySession().getUserId());
        step6Mapper.updateMileagePaymentChkForCancel(option);
    }
}
