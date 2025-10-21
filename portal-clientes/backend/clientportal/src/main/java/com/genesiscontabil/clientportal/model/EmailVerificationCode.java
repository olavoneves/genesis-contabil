package com.genesiscontabil.clientportal.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "email_verification_codes")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class EmailVerificationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expiration;

    @Column(nullable = false)
    private boolean used = false;

    public EmailVerificationCode(User user, String code, LocalDateTime expiration) {
        this.user = user;
        this.code = code;
        this.expiration = expiration;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiration);
    }
}
