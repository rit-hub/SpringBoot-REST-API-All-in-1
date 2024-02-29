package com.rithub.webclient.service.impl;

import com.rithub.webclient.client.BusinessClient;
import com.rithub.webclient.client.model.ClientRequest;
import com.rithub.webclient.client.model.ClientResponse;
import com.rithub.webclient.model.BusinessRequest;
import com.rithub.webclient.model.BusinessResponse;
import com.rithub.webclient.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessClient businessClient;
    @Override
    public Mono<BusinessResponse> getBusinessData(final BusinessRequest businessRequest, final String requestId) {
        // STEP 1 - Map Business Request to Client Request
        ClientRequest clientRequest = ClientRequest.builder().build();

        // STEP 2 - Get Client Response Data by calling Client using Client Request
        Mono<ClientResponse> clientData = businessClient.getClientData(clientRequest);

        // STEP 3 - Map Client Response to Business Response
        Mono<BusinessResponse> businessResponseMono = clientData.map(clientResponse -> {
            String[] splitNameArray = clientResponse.getFullName().split(" ");
            return BusinessResponse.builder()
                    .firstName(splitNameArray[0])
                    .lastName(splitNameArray[1])
                    .balance(clientResponse.getAmount().getBalance())
                    .currency(clientResponse.getAmount().getCurrencyCode())
                    .build();
        });

        // STEP 4 - return Business Response
        return businessResponseMono;
    }
}
