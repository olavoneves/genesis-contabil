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
@Table(name = "admin_uploads")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class AdminUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String originalFileName;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false)
    private String storagePath;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private AdminUser uploadedBy;

    @CreationTimestamp
    private LocalDateTime uploadedAt;

    @Column(nullable = false)
    private Boolean processed = false;
}
