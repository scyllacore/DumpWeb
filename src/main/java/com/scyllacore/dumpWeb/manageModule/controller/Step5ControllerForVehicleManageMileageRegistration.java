package com.scyllacore.dumpWeb.manageModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.TDrive;
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
    @PostMapping(value = "/ajax/save")
    @ResponseBody
    public String mileageSave(TDrive mileage) {
        return step5Service.saveMileage(mileage);
    }

    @PostMapping(value = "/ajax/list")
    @ResponseBody
    public List<TDrive> mileageList(String date) {
        return step5Service.findMileageList(date);
    }

    @DeleteMapping(value = "/ajax/delete")
    @ResponseBody
    public void mileageRemove(int driveID) {
        step5Service.removeMileage(driveID);
    }

    /* param.js */
    @PostMapping(value = "/ajax/details")
    @ResponseBody
    public TDrive mileageDetails(int driveID) {
        return step5Service.findMileage(driveID);
    }

}
