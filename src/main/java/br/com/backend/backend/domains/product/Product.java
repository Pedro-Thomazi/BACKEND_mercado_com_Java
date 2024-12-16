package br.com.backend.backend.domains.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "products")
@Entity(name = "Products")
@Getter
@Setter
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean in_stock;
    private String url_image;

    public Product(){}

    public Product(DataProduct data) {
        this.name = data.name();
        this.description = data.description();
        this.price = data.price();
        this.in_stock = true;
        this.url_image = data.urlImage();
    }

    public void alterProduct(DataAlterProduct data) {
        if (data.name() != null) {
            this.name = data.name();
        }

        if (data.description() != null) {
            this.description = data.description();
        }

        if (data.price() != null) {
            this.price = data.price();
        }

        if (data.urlImage() != null) {
            this.url_image = data.urlImage();
        }
    }

    public void notInStock() {
        this.in_stock = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getInStock() {
        return in_stock;
    }

    public String getImage() {
        return url_image;
    }
}
