package com.rithub.webclient.controller;

import com.rithub.webclient.model.BusinessRequest;
import com.rithub.webclient.model.BusinessResponse;
import com.rithub.webclient.service.impl.BusinessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bapi")
public class BusinessApi {

    @Autowired
    private BusinessServiceImpl businessService;

    @PostMapping("/GetBusinessResponse/Data")
    public Mono<ResponseEntity<BusinessResponse>> getBusinessResponse(
            @RequestHeader(value = "RequestId") String requestId,
            @RequestBody BusinessRequest businessRequest) {
        Mono<BusinessResponse> businessResponseMono = businessService.getBusinessData(businessRequest, requestId);
        return businessResponseMono.map(businessResponse -> new ResponseEntity<>(businessResponse, HttpStatus.OK));
    }
}
