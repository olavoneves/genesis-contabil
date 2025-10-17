package com.genesiscontabil.adminportal.service;

import com.genesiscontabil.adminportal.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository repository;

    
}
