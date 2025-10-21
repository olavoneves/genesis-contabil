package com.genesiscontabil.clientportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationCodeEmail(String to, String code) {
        String subject = "Código de verificação - Redefinição de senha";
        String text = String.format(
                "Você solicitou a redefinição de senha.\n\nSeu código de verificação é: %s\n\nEle expira em 10 minutos.",
                code
        );

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
