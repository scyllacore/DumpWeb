package com.scyllacore.dumpWeb.manageModule.service;

import com.scyllacore.dumpWeb.commonModule.db.dto.manage.SummaryDTO;
import com.scyllacore.dumpWeb.commonModule.db.mapper.manage.Step1ForDriverMenuMapper;
import com.scyllacore.dumpWeb.commonModule.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Step1ForDriverMenuService {

    private final Step1ForDriverMenuMapper step1Mapper;
    private final SessionUtil sessionUtil;


    public ResponseEntity<SummaryDTO.Response>  calculateSummary(SummaryDTO.Request date){
        date.setDriverIdFk(sessionUtil.getDriverInfo().getDriverId());

        SummaryDTO.Response response = new SummaryDTO.Response();
        response.setTotalTransportationCost(step1Mapper.calculateTotalTransportationCost(date));
        response.setTotalTrips(step1Mapper.calculateTotalTrips(date));

        return ResponseEntity.ok(response);
    }

}
