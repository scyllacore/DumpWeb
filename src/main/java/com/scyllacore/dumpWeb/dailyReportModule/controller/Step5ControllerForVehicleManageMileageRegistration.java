package com.scyllacore.dumpWeb.dailyReportModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.TDrive;
import com.scyllacore.dumpWeb.dailyReportModule.service.Step5ServiceForVehicleManageMileageRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage/step5")
@RequiredArgsConstructor
public class Step5ControllerForVehicleManageMileageRegistration {

    private final Step5ServiceForVehicleManageMileageRegistration step5Service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String step5() {
        return "/manage/step5/step5Index";
    }

    /* click.js */
    @RequestMapping(value = "/ajax/save", method = RequestMethod.POST)
    @ResponseBody
    public String tDriveSave(TDrive tDrive) {
        return step5Service.saveTDrive(tDrive);
    }

    @RequestMapping(value = "/ajax/list", method = RequestMethod.POST)
    @ResponseBody
    public List<TDrive> tDriveList(String date) {
        return step5Service.findTDriveList(date);
    }

    @RequestMapping(value = "/ajax/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void tDriveRemove(int driveID) {
        step5Service.removeTDrive(driveID);
    }

    /* param.js */
    @RequestMapping(value = "/ajax/details", method = RequestMethod.POST)
    @ResponseBody
    public TDrive tDriveDetails(int driveID) {
        return step5Service.findTDriveDetails(driveID);
    }
    
}
