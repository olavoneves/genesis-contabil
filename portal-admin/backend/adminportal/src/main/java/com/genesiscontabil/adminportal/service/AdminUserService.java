package com.genesiscontabil.adminportal.service;

import com.genesiscontabil.adminportal.dto.AdminUserDTO;
import com.genesiscontabil.adminportal.model.AdminUser;
import com.genesiscontabil.adminportal.repository.AdminUserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AdminUserService implements UserDetailsService {

    @Autowired
    private AdminUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUser adminUser = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

        return User.builder()
                .username(adminUser.getUsername())
                .password(adminUser.getPassword())
                .roles("ADMIN")
                .build();
    }

    public ResponseEntity save(AdminUserDTO adminUser, UriComponentsBuilder uriComponentsBuilder) {
        var admin = new AdminUser(adminUser);
        repository.save(admin);

        var uri = uriComponentsBuilder.path("/api/admin-user/{id}").buildAndExpand(admin.getId()).toUri();

        return ResponseEntity.created(uri).body(new AdminUserDTO(admin));
    }
}
