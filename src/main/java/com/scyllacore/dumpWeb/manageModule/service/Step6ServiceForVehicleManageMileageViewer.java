package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SearchOption;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.TDrive;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step6MapperForVehicleManageMileageViewer;
import com.scyllacore.dumpWeb.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Step6ServiceForVehicleManageMileageViewer {
    private final Step6MapperForVehicleManageMileageViewer step6Mapper;
    private final CommonUtil commonUtil;


    public List<TDrive> findMileageListByOption(SearchOption option) {
        option.setCarNo(commonUtil.getLoginInfoBySession().getUserId());
        return step6Mapper.selectMileageListByOption(option);
    }

    public void approvePaymentByMileageChk2(SearchOption option) {
        option.setCarNo(commonUtil.getLoginInfoBySession().getUserId());
        step6Mapper.updateMileageChk2ForApprove(option);
    }

    public void cancelPaymentByMileageChk2(SearchOption option) {
        option.setCarNo(commonUtil.getLoginInfoBySession().getUserId());
        step6Mapper.updateMileageChk2ForCancel(option);
    }
}
