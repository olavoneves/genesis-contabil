package com.genesiscontabil.clientportal.repository;

import com.genesiscontabil.clientportal.model.EmailVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerificationCode, UUID> {
    Optional<EmailVerificationCode> findByCode(String code);
}
