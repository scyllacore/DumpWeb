package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SearchOption;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.TDrive;
import com.scyllacore.dumpWeb.manageModule.service.Step6ServiceForVehicleManageMileageViewer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step6")
@RequiredArgsConstructor
public class Step6ControllerForVehicleManageMileageViewer {

    private final Step6ServiceForVehicleManageMileageViewer step6Service;

    @GetMapping(value = "")
    public String step6() {
        return "/manage/step6/step6_index";
    }

    @PostMapping(value = "/ajax/mileageList")
    @ResponseBody
    public List<TDrive> mileageList(SearchOption option) {
        return step6Service.findMileageListByOption(option);
    }

    @PostMapping(value = "/ajax/paymentApproval")
    public void paymentApproval(@RequestBody SearchOption option) {
        step6Service.approvePaymentByMileageChk2(option);
    }

    @PostMapping(value = "/ajax/paymentCancel")
    public void paymentCancel(@RequestBody SearchOption option) {
        step6Service.cancelPaymentByMileageChk2(option);
    }


}
