package com.genesiscontabil.adminportal.repository;

import com.genesiscontabil.adminportal.model.UploadedPdf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UploadedPdfRepository extends JpaRepository<UploadedPdf, UUID> {
    List<UploadedPdf> findByClientIdentifier(String clientIdentifier);
}
