package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.http.dto.ResponseDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step5ForVehicleManageMileageRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step5")
@RequiredArgsConstructor
public class Step5ForVehicleManageMileageRegistrationController {

    private final Step5ForVehicleManageMileageRegistrationService step5Service;

    @GetMapping(value = "")
    public String step5() {
        return "/manage/step5/step5_index";
    }

    @PostMapping(value = "/fetch/mileageSave")
    @ResponseBody
    public ResponseDTO<String> mileageSave(@RequestBody MileageDTO mileage) {
        return step5Service.saveMileage(mileage);
    }

    @PostMapping(value = "/fetch/mileageList")
    @ResponseBody
    public ResponseDTO<List<MileageDTO>> mileageList(@RequestBody MileageDTO mileage) {
        return step5Service.findMileageList(mileage);
    }

    @DeleteMapping(value = "/fetch/mileageRemove")
    @ResponseBody
    public ResponseDTO<String> mileageRemove(@RequestBody MileageDTO mileage) {
        return step5Service.removeMileage(mileage);
    }

    @PostMapping(value = "/fetch/mileageDetails")
    @ResponseBody
    public ResponseDTO<MileageDTO> mileageDetails(@RequestBody MileageDTO mileage) {
        return step5Service.findMileage(mileage);
    }

}
