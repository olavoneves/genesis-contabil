package com.genesiscontabil.adminportal.service;

import com.genesiscontabil.adminportal.model.AdminUser;
import com.genesiscontabil.adminportal.model.PasswordResetToken;
import com.genesiscontabil.adminportal.repository.AdminUserRepository;
import com.genesiscontabil.adminportal.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    @Autowired
    private AdminUserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (resetToken.isUsed()) {
            throw new RuntimeException("Token expirado");
        }

        AdminUser user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        tokenRepository.delete(resetToken);
    }
}
