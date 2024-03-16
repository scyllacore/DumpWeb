package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.commonModule.http.ResponseDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step5ForVehicleManageMileageRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDTO<String>> mileageSave(@Valid @RequestBody MileageDTO.Request mileage) {
        return step5Service.saveMileage(mileage);
    }

    @PostMapping(value = "/fetch/mileageList")
    @ResponseBody
    public ResponseEntity<List<MileageDTO.Response>> mileageList(@RequestBody MileageDTO.Request mileage) {
        return step5Service.findMileageList(mileage);
    }

    @DeleteMapping(value = "/fetch/mileageRemove")
    @ResponseBody
    public ResponseEntity<ResponseDTO<String>> mileageRemove(@RequestBody MileageDTO.Request mileage) {
        return step5Service.removeMileage(mileage);
    }

    @PostMapping(value = "/fetch/mileageDetails")
    @ResponseBody
    public ResponseEntity<MileageDTO.Response> mileageDetails(@RequestBody MileageDTO.Request mileage) {
        return step5Service.findMileage(mileage);
    }

}
