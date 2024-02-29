package com.rithub.webclient.client;

import com.rithub.webclient.client.model.ClientRequest;
import com.rithub.webclient.client.model.ClientResponse;
import com.rithub.webclient.config.WebClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class BusinessClient {

    @Autowired
    private WebClientConfig webClientConfig;

    public Mono<ClientResponse> getClientData(ClientRequest clientRequest) {
        return webClientConfig.getWebClient().post()
                .uri("localhost:8081")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "123")
                .bodyValue(clientRequest)
                .retrieve()
                .bodyToMono(ClientResponse.class)
                .switchIfEmpty(Mono.error(new RuntimeException("No data found at SOR")))
                .subscribeOn(Schedulers.boundedElastic());
    }

}
