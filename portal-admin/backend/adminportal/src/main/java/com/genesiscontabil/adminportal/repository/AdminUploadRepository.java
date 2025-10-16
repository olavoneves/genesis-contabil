package com.genesiscontabil.adminportal.repository;

import com.genesiscontabil.adminportal.model.AdminUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminUploadRepository extends JpaRepository<AdminUpload, UUID> {

}
