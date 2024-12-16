package br.com.backend.backend.domains.product;

public record DataProduct(
        String name,
        String description,
        Double price,
        Boolean inStock,
        String urlImage
) {
}
