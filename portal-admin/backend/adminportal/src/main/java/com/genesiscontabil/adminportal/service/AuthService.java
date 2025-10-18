package com.genesiscontabil.adminportal.service;

import com.genesiscontabil.adminportal.dto.LoginRequest;
import com.genesiscontabil.adminportal.dto.LoginResponse;
import com.genesiscontabil.adminportal.dto.ResetRequest;
import com.genesiscontabil.adminportal.model.AdminUser;
import com.genesiscontabil.adminportal.model.PasswordResetToken;
import com.genesiscontabil.adminportal.repository.AdminUserRepository;
import com.genesiscontabil.adminportal.repository.PasswordResetTokenRepository;
import com.genesiscontabil.adminportal.security.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MailService mailService;

    /**
     * LOGIN → Gera token JWT se credenciais forem válidas
     */
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        AdminUser admin = adminUserRepository.findByUsername(request.getIdentifier())
                .orElseThrow(() -> new BadCredentialsException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new BadCredentialsException("Senha incorreta");
        }

        String token = jwtUtil.generateToken(admin.getUsername());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    /**
     * REQUEST RESET → Gera token de redefinição e envia e-mail
     */
    @Transactional
    public ResponseEntity<String> requestReset(Map<String, String> body) {
        String identifier = body.get("identifier");
        Optional<AdminUser> userOpt = adminUserRepository.findByUsername(identifier);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado");
        }

        AdminUser user = userOpt.get();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setUser(user);
        resetToken.setToken(UUID.randomUUID().toString());
        resetToken.setExpiresAt(LocalDateTime.now().plusHours(1));
        tokenRepository.save(resetToken);

        mailService.sendResetEmail(user.getUsername(), resetToken.getToken());
        return ResponseEntity.ok("E-mail de redefinição enviado!");
    }

    /**
     * RESET → Valida token e redefine senha
     */
    @Transactional
    public ResponseEntity<String> reset(ResetRequest resetRequest) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(resetRequest.getToken());

        if (tokenOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Token inválido");
        }

        PasswordResetToken token = tokenOpt.get();

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(token);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Token expirado");
        }

        AdminUser user = token.getUser();
        user.setPassword(passwordEncoder.encode(resetRequest.getNewPassword()));
        adminUserRepository.save(user);

        tokenRepository.delete(token);
        return ResponseEntity.ok("Senha redefinida com sucesso!");
    }
}
