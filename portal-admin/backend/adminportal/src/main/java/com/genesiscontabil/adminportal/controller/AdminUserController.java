package com.genesiscontabil.adminportal.controller;

import com.genesiscontabil.adminportal.dto.AdminUserDTO;
import com.genesiscontabil.adminportal.service.AdminUserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class AdminUserController {

    @Autowired
    private AdminUserService service;

    @PostMapping("/admin-user")
    @Transactional
    public ResponseEntity save(@RequestBody @Valid AdminUserDTO adminUser, UriComponentsBuilder uri) {
        return service.save(adminUser, uri);
    }
}
