package com.genesiscontabil.adminportal.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "upload_audit")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UploadAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AdminUser admin;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String target;

    @Column(columnDefinition = "TEXT")
    private String meta;

    @CreationTimestamp
    private LocalDateTime timestamp;
}
