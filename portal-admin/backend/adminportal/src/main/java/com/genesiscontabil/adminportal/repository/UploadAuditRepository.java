package com.genesiscontabil.adminportal.repository;

import com.genesiscontabil.adminportal.model.UploadAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UploadAuditRepository extends JpaRepository<UploadAudit, UUID> {

}
