package com.genesiscontabil.clientportal.controller;

import com.genesiscontabil.clientportal.dto.ClientLoginRequest;
import com.genesiscontabil.clientportal.dto.ClientLoginResponse;
import com.genesiscontabil.clientportal.service.ClientAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client/auth")
public class ClientAuthController {

    @Autowired
    private ClientAuthService service;

    @PostMapping("/login")
    public ResponseEntity<ClientLoginResponse> login(@RequestBody @Valid ClientLoginRequest request) {
        return service.login(request);
    }

    @PostMapping("/request")
    public ResponseEntity<String> requestVerification(@RequestParam String email) {
        return service.requestVerification(email);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam String code) {
        return service.verifyCode(email, code);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        return service.resetPassword(email, newPassword);
    }
}
