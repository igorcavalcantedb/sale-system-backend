package com.eusosys.salelist.controllers;

import com.eusosys.salelist.services.SaleService;
import com.eusosys.salelist.services.SmsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sales")
public class SaleController {
    private final SaleService service;
    private final SmsService smsService;

    public SaleController(SaleService service, SmsService smsService) {
        this.service = service;
        this.smsService = smsService;
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page> findAll(Pageable pageable){
        var response = service.findAll(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping()
    public ResponseEntity<Page> findByInterval(@RequestParam(value = "start",defaultValue = "") String start,
                                         @RequestParam(value = "end",defaultValue = "") String end,
                                         Pageable pageable){
        var response = service.findByInterval(start,end,pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/notification") // TODO /{id}/notification
    public void notifyBySms(){
        smsService.sendSms();
        }
}
