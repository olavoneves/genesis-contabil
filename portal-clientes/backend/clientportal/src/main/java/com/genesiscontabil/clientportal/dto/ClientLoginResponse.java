package com.genesiscontabil.clientportal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientLoginResponse {
    private String token;
    private String refreshToken;
    private String nome;
}
