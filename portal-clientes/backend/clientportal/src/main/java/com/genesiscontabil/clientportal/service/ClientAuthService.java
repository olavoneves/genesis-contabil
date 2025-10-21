package com.genesiscontabil.clientportal.service;

import com.genesiscontabil.clientportal.dto.ClientLoginRequest;
import com.genesiscontabil.clientportal.dto.ClientLoginResponse;
import com.genesiscontabil.clientportal.model.User;
import com.genesiscontabil.clientportal.repository.UserRepository;
import com.genesiscontabil.clientportal.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientAuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<ClientLoginResponse> login(ClientLoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCpfCnpj(), request.getPassword()));

        User user = repository.findByIdentifier(request.getCpfCnpj())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        String token = jwtUtil.generateToken(user.getIdentifier());
        String refreshToken = jwtUtil.generateRefreshToken(user.getIdentifier());

        return ResponseEntity.ok(new ClientLoginResponse(token, refreshToken, user.getName()));
    }
}
