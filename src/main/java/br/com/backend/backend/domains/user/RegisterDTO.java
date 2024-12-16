package br.com.backend.backend.domains.user;

import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        String name,
        String email,
        String password,
        StatusUser status
) {
}
