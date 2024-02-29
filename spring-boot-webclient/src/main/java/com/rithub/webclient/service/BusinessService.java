package com.rithub.webclient.service;

import com.rithub.webclient.model.BusinessRequest;
import com.rithub.webclient.model.BusinessResponse;
import reactor.core.publisher.Mono;

public interface BusinessService {
    public Mono<BusinessResponse> getBusinessData(BusinessRequest businessRequest, final String requestId);
}
