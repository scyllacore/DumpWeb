package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageSearchOptionDTO;
import com.scyllacore.dumpWeb.commonModule.db.dto.manage.PageDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step6ForVehicleManageMileageViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;


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
    public ResponseEntity<PageDTO> mileageList(@RequestBody MileageSearchOptionDTO.Request option) {
        return step6Service.findMileageListByOption(option);
    }

    @PutMapping(value = "/fetch/paymentInBulk")
    @ResponseBody
    public ResponseEntity<ResponseDTO<String>> paymentInBulk(@RequestBody MileageSearchOptionDTO.Request option) {
        return step6Service.modifyPaymentInBulk(option);
    }

}
