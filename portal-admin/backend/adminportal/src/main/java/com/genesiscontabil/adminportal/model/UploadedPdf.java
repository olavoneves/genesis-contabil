package com.genesiscontabil.adminportal.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "uploaded_pdfs")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UploadedPdf {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String storagePath;

    @Column(nullable = false)
    private LocalDate competencyMonth;

    @Column(nullable = false)
    private String clientIdentifier;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private AdminUser uploadedBy;

    @CreationTimestamp
    private LocalDateTime uploadedAt;

}
