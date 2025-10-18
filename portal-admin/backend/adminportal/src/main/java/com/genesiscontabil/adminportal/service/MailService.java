package com.genesiscontabil.adminportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender sender;

    public void sendResetEmail(String to, String token) {
        final String LINK = "http://localhost:3000/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Genesis Contábil - Redefinição de Senha");
        message.setText("""
                Você solicitou a redefinição de senha.
                Clique no link abaixo para redefinir:
                %s
                
                Atenção: o link expira em 60 minutos.
                """.formatted(LINK));

        sender.send(message);
    }
}
