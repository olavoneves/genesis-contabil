package com.genesiscontabil.clientportal.service;

import com.genesiscontabil.clientportal.model.EmailVerificationCode;
import com.genesiscontabil.clientportal.model.User;
import com.genesiscontabil.clientportal.repository.EmailVerificationRepository;
import com.genesiscontabil.clientportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class EmailVerificationService {

    @Autowired
    private EmailVerificationRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public void sendVerificationCode(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com este e-mail."));

        String code = generateCode();
        LocalDateTime expiration = LocalDateTime.now().plusMinutes(10);

        EmailVerificationCode verification = new EmailVerificationCode(user, code, expiration);
        repository.save(verification);

        emailService.sendVerificationCodeEmail(email, code);
    }

    public boolean verifyCode(String email, String code) {
        EmailVerificationCode verification = repository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Código inválido."));

        if (!verification.getUser().getEmail().equals(email))
            throw new RuntimeException("E-mail não corresponde ao código informado.");

        if (verification.isExpired() || verification.isUsed())
            throw new RuntimeException("Código expirado ou já utilizado.");

        verification.setUsed(true);
        repository.save(verification);
        return true;
    }

    private String generateCode() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
