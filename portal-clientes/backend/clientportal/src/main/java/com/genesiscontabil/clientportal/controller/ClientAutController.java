package com.genesiscontabil.clientportal.controller;

import com.genesiscontabil.clientportal.dto.ClientLoginRequest;
import com.genesiscontabil.clientportal.dto.ClientLoginResponse;
import com.genesiscontabil.clientportal.service.ClientAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/auth")
public class ClientAutController {

    @Autowired
    private ClientAuthService service;

    @PostMapping("/login")
    public ResponseEntity<ClientLoginResponse> login(@RequestBody @Valid ClientLoginRequest request) {
        return service.login(request);
    }
}
