package com.genesiscontabil.adminportal.dto;

import com.genesiscontabil.adminportal.model.AdminUser;
import jakarta.validation.constraints.NotNull;

public record AdminUserDTO(@NotNull String username,
                           @NotNull String password) {
    public AdminUserDTO(AdminUser admin) {
        this(admin.getUsername(), admin.getPassword());
    }
}
