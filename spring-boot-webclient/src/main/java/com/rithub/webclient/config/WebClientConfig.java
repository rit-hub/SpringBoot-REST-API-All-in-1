package com.rithub.webclient.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    public WebClient getWebClient(){
        return WebClient.builder().build();
    }
}
