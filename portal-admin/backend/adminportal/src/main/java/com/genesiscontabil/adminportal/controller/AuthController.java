package com.genesiscontabil.adminportal.controller;

import com.genesiscontabil.adminportal.dto.LoginRequest;
import com.genesiscontabil.adminportal.dto.LoginResponse;
import com.genesiscontabil.adminportal.dto.ResetRequest;
import com.genesiscontabil.adminportal.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return service.login(request);
    }

    @PostMapping("/request-reset")
    public ResponseEntity<String> requestReset(@RequestBody Map<String, String> body) {
        return service.requestReset(body);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> reset(@RequestBody @Valid ResetRequest resetRequest) {
        return service.reset(resetRequest);
    }
}
