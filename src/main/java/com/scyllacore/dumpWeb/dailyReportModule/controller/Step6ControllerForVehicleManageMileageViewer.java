package com.scyllacore.dumpWeb.dailyReportModule.controller;

import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.SearchOption;
import com.scyllacore.dumpWeb.commonModule.db.dto.dailyreport.TDrive;
import com.scyllacore.dumpWeb.dailyReportModule.service.Step6ServiceForVehicleManageMileageViewer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage/step6")
@RequiredArgsConstructor
public class Step6ControllerForVehicleManageMileageViewer {

    private final Step6ServiceForVehicleManageMileageViewer step6Service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String step6() {
        return "/manage/step6/carcarelist";
    }

    @RequestMapping(value = "/ajax/list", method = RequestMethod.POST)
    @ResponseBody
    public List<TDrive> tDriveList(SearchOption option) {
        return step6Service.findCarListByOption(option);
    }

    @RequestMapping(value = "/ajax/process", method = RequestMethod.POST)
    @ResponseBody
    public void tDriveChk2Process(@RequestBody SearchOption option) {
        step6Service.processTDriveChk2(option);
    }

    @RequestMapping(value = "/ajax/cancel", method = RequestMethod.POST)
    @ResponseBody
    public void tDriveChk2Cancel(@RequestBody SearchOption option) {
        step6Service.cancelTDriveChk2(option);
    }


}
