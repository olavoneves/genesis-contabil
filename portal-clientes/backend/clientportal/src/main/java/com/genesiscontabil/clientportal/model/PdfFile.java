package com.genesiscontabil.clientportal.model;

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
@Table(name = "pdf_files")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PdfFile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 300)
    private String fileName;

    @Column(nullable = false, length = 600)
    private String storagePath;

    @Column(nullable = false)
    private LocalDate competencyMonth;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    private LocalDateTime uploadedAt;
}
