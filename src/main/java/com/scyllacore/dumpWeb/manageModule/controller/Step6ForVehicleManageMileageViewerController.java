package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.PageCriteriaDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.PageDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step6ForVehicleManageMileageViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manage/step6")
@RequiredArgsConstructor
public class Step6ForVehicleManageMileageViewerController {

    private final Step6ForVehicleManageMileageViewerService step6Service;

    @GetMapping(value = "")
    public String step6() {
        return "/manage/step6/step6_index";
    }

    @PostMapping(value = "/fetch/mileageList")
    @ResponseBody
    public ResponseDTO<PageDTO> mileageList(@RequestBody MileageSearchOptionDTO option) {
        return step6Service.findMileageListByOption(option);
    }

    @PutMapping(value = "/fetch/paymentInBulk")
    @ResponseBody
    public ResponseDTO<String> paymentInBulk(@RequestBody MileageSearchOptionDTO option) {
        return step6Service.modifyPaymentInBulk(option);
    }

}
