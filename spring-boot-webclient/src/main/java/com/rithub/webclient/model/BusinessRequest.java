package com.rithub.webclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessRequest {

    @JsonProperty("UserName")
    private String userName;

    @JsonProperty("Password")
    private String password;
}
