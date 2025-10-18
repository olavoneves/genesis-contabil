package com.genesiscontabil.adminportal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetRequest {
    private String email;
    private String token;
    private String newPassword;
}
