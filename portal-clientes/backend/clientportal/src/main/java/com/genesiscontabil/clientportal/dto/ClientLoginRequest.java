package com.genesiscontabil.clientportal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientLoginRequest {

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    private String cpfCnpj;

    @NotBlank(message = "Senha é obrigatória")
    private String password;
}
