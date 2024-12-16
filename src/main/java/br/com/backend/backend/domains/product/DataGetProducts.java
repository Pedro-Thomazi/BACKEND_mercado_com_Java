package br.com.backend.backend.domains.product;

public record DataGetProducts(
        Long id,
        String name,
        String description,
        Double price,
        Boolean inStock,
        String imageUrl
) {
    public DataGetProducts(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getInStock(), product.getImage());
    }
}
