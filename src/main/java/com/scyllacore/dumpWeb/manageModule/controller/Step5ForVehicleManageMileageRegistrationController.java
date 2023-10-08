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

    /* click.js */
    @PostMapping(value = "/fetch/mileageSave")
    @ResponseBody
    public ResponseDTO<String> mileageSave(@RequestBody MileageDTO mileage) {
        return step5Service.saveMileage(mileage);
    }

    @PostMapping(value = "/fetch/mileageList")
    @ResponseBody
    public List<MileageDTO> mileageList(String date) {
        return step5Service.findMileageList(date);
    }

    @DeleteMapping(value = "/fetch/mileageRemove")
    public void mileageRemove(int driveID) {
        step5Service.removeMileage(driveID);
    }

    /* param.js */
    @PostMapping(value = "/fetch/mileageDetails")
    @ResponseBody
    public MileageDTO mileageDetails(int driveID) {
        return step5Service.findMileage(driveID);
    }

}
