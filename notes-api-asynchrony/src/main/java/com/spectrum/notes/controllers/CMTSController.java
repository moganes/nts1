package com.spectrum.notes.controllers;

import com.spectrum.notes.dao.CmtsDailyService;
import com.spectrum.notes.models.CableModemData;
import com.spectrum.notes.models.CableModem5Min;
import com.spectrum.notes.models.CableModemDaily;
import com.spectrum.notes.models.CmtsDaily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/cmts")
public class CMTSController {

    private final CmtsDailyService cmtsDailyService;

    @Autowired
    public CMTSController(CmtsDailyService cmtsDailyService) {
        this.cmtsDailyService = cmtsDailyService;
    }

    @GetMapping
    public ResponseEntity<CmtsDaily> getCmtsDailyById(@RequestParam(name = "id") String id) throws InterruptedException, ExecutionException {
        return cmtsDailyService
                .getCmtsDailyById(id)
                .get()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(path = "/cm5")
    public ResponseEntity<Collection<CableModem5Min>> getCableModemFiveMinByCmtsId(@RequestParam(name = "id") String id) throws InterruptedException, ExecutionException {
//        return influxDBClient
//                .getCableModem5MinByCmts(id)
//                .get()
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.noContent().build());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/cmDaily")
    public ResponseEntity<Collection<CableModemDaily>> getCableModemDailyByCmtsId(@RequestParam(name = "id") String id) throws InterruptedException, ExecutionException {
        return cmtsDailyService
                .getCableModemDailyByCmts(id)
                .get()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(path = "/cm")
    public ResponseEntity<Collection<CableModemData>> getCableModemByCmtsId(@RequestParam(name = "id") String id) throws InterruptedException, ExecutionException {
        return cmtsDailyService
                .getCableModemByCmts(id)
                .get()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}
