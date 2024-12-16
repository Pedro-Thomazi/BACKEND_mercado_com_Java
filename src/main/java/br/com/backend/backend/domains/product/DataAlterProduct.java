package br.com.backend.backend.domains.product;

import jakarta.validation.constraints.NotNull;

public record DataAlterProduct(
        @NotNull
        Long id,
        String name,
        String description,
        Double price,
        String urlImage
) {
}
