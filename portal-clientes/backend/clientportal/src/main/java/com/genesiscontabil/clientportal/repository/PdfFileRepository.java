package com.genesiscontabil.clientportal.repository;

import com.genesiscontabil.clientportal.model.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PdfFileRepository extends JpaRepository<PdfFile, UUID> {
    List<PdfFile> findByUserId(UUID userId);
}
