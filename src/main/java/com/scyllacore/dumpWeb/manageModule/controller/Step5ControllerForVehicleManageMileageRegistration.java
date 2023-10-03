package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.MileageDTO;
import com.scyllacore.dumpWeb.manageModule.service.Step5ServiceForVehicleManageMileageRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/step5")
@RequiredArgsConstructor
public class Step5ControllerForVehicleManageMileageRegistration {

    private final Step5ServiceForVehicleManageMileageRegistration step5Service;

    @GetMapping(value = "")
    public String step5() {
        return "/manage/step5/step5_index";
    }

    /* click.js */
    @PostMapping(value = "/ajax/mileageSave")
    @ResponseBody
    public String mileageSave(MileageDTO mileage) {
        return step5Service.saveMileage(mileage);
    }

    @PostMapping(value = "/ajax/mileageList")
    @ResponseBody
    public List<MileageDTO> mileageList(String date) {
        return step5Service.findMileageList(date);
    }

    @DeleteMapping(value = "/ajax/mileageRemove")
    public void mileageRemove(int driveID) {
        step5Service.removeMileage(driveID);
    }

    /* param.js */
    @PostMapping(value = "/ajax/mileageDetails")
    @ResponseBody
    public MileageDTO mileageDetails(int driveID) {
        return step5Service.findMileage(driveID);
    }

}
