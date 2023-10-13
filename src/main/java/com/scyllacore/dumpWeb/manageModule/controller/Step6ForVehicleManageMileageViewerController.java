package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step6ForVehicleManageMileageViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseDTO<List<MileageDTO>> mileageList(@RequestBody SearchOptionDTO option) {
        System.out.println(option);
        return step6Service.findMileageListByOption(option);
    }

    @PutMapping(value = "/fetch/paymentInBulk")
    @ResponseBody
    public ResponseDTO<String> paymentInBulk(@RequestBody SearchOptionDTO option) {
        return step6Service.modifyPaymentInBulk(option);
    }

}
